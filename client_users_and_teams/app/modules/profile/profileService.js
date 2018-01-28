(function() {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.service:profileService
	 * @description
	 * # profileService
	 * Service of the app
	 */

  	angular
		.module('users-and-teams')
		.factory('profileService', profileService);

		profileService.$inject = ['$http','homeService'];

		function profileService ($http, homeService) {

			// Get the users informations
			// Call GET api/users/{username}
			function getUser(username){
				var req = {
					method: 'GET',
					url: 'http://localhost:8080/api/users/'+ username,
					headers: {
						'Authorization': 'Bearer ' + homeService.getToken(),
						'Accept': 'application/json',
					},
				};
	
				$http(req)
					.then(function (response) {
						homeService.setUserInfos(response.data);
						var status = { status: response.status, message: response.statusText, data:{} };
						homeService.setStatus(status);
					}, function (error) {
						var status = { status: error.status, message: error.statusText, data:{} };
						homeService.setStatus(status);
					}
				);
			}

			// Update the user account
			// Call PUT api/users/{username}
			function updateUser(username, updateData){
				var req = {
					method: 'PUT',
					url: 'http://localhost:8080/api/users/' + username,
					headers: {
						'Authorization': 'Bearer ' + homeService.getToken(),
						'Accept': 'application/json',
					},
					data: updateData,
				};

				$http(req)
					.then(function (response) {
						// update user infos
						homeService.setUserInfos(
							{
								username: updateData.username,
								firstName: updateData.firstName,
								lastName: updateData.lastName,
								id: updateData.id,
								email: updateData.email,
							}
						);
	
						var status = { status: response.status, message: response.statusText, data:{} };
						homeService.setStatus(status);
					}, function (error) {
						var status = { status: error.status, message: error.statusText, data:{} };
						homeService.setStatus(status);
					});
			};

			// Get teams of user
			// Call GET api/users/{username}/teams
			function getTeamsOfUser(username){
				var req = {
					method: 'GET',
					url: 'http://localhost:8080/api/users/' + username +'/teams',
					headers: {
						'Authorization': 'Bearer ' + homeService.getToken(),
						'Accept': 'application/json',
					},
				};

				$http(req)
					.then(function (response) {
						homeService.setListMyTeams(response.data);
						var status = { status: response.status, message: response.statusText, data:{} };
						homeService.setStatus(status);		
					}, function (error) {
						var status = { status: error.status, message: error.statusText, data:{} };
						homeService.setStatus(status);
					});
			};

			// Get a team with id of team
			// Call GET sur api/teams/{id_team}
			function getTeam(idTeam){
				var req = {
					method: 'GET',
					url: 'http://localhost:8080/api/teams/' + idTeam,
					headers: {
						'Authorization': 'Bearer ' + homeService.getToken(),
						'Accept': 'application/json',
					},
				};

				$http(req)
					.then(function (response) {
						var status = { status: response.status, message: response.statusText, data:{} };
						homeService.setStatus(status);	
					}, function (error) {
						var status = { status: error.status, message: error.statusText, data:{} };
						homeService.setStatus(status);
					});
			};

			// Create a new team
			// Call POST api/users/teams
			function createTeam(name){
				var infos = homeService.getUserInfos();
				// dataset for the POST request
				var datas = {
						members: [
						  {
							email: infos.email,
							firstName: infos.firstName,
							id: infos.id,
							lastName: infos.lastName,
							username: infos.username,
						  }
						],
						name: name
					  };

				var req = {
					method: 'POST',
					url: 'http://localhost:8080//api/teams',
					headers: {
						'Authorization': 'Bearer ' + homeService.getToken(),
						'Accept': 'application/json',
					},
					data: datas
				};

				$http(req)
					.then(function (response) {
						var status = { status: response.status, message: response.statusText, data:{} };
						homeService.setStatus(status);
					}, function (error) {
						var status = { status: error.status, message: error.statusText, data:{} };
						homeService.setStatus(status);
					}
				);
			};

			// Update a team
			// Call PUT api/teams
			function updateTeam(team, newMember){
				var datas = {
					id: team.id,
					name: team.name,
					members: team.members,
				};

				datas.members.push(newMember);

				var req = {
					method: 'PUT',
					url: 'http://localhost:8080//api/teams',
					headers: {
						'Authorization': 'Bearer ' + homeService.getToken(),
						'Accept': 'application/json',
					},
					data: datas
				};
				console.log(req);

					$http(req)
						.then(function (response) {
							var status = { status: response.status, message: response.statusText, data:{} };
							homeService.setStatus(status);
						}, function (error) {
							var status = { status: error.status, message: error.statusText, data:{} };
							homeService.setStatus(status);
						}
					);
				};

			// Get the users informations
			// Call GET api/users/{username}
			function getUsers(){
				var req = {
					method: 'GET',
					url: 'http://localhost:8080/api/users/',
					headers: {
						'Authorization': 'Bearer ' + homeService.getToken(),
						'Accept': 'application/json',
					},
				};
	
				$http(req)
					.then(function (response) {
						homeService.setListUsers(response.data);
						var status = { status: response.status, message: response.statusText, data:{} };
						homeService.setStatus(status);
					}, function (error) {
						var status = { status: error.status, message: error.statusText, data:{} };
						homeService.setStatus(status);
					}
				);
			}

			return{
				getUser: getUser,
				updateUser: updateUser,
				getTeamsOfUser: getTeamsOfUser,
				getTeam: getTeam,
				createTeam: createTeam,
				getUsers: getUsers,
				updateTeam: updateTeam,
			};
		}

})();
