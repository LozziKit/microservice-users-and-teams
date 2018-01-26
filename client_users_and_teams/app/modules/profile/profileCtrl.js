(function() {
	'use strict';

	/**
	* @ngdoc function
	* @name app.controller:profileCtrl
	* @description
	* # profileCtrl
	* Controller of the app
	*/

  	angular
		.module('users-and-teams')
		.controller('ProfileCtrl', Profile);

		Profile.$inject = ['profileService', 'homeService', '$timeout'];
		
		/*
		* recommend
		* Using function declarations
		* and bindable members up top.
		*/

		function Profile(profileService,homeService, $timeout) {
			/*jshint validthis: true */
			var vm = this;

			// show/hide views
			vm.showUpdate = false;
			vm.isCreated = false;

			vm.token = homeService.getToken();
			vm.profile = homeService.getUserInfos();
			vm.listTeams = { 
				teams: homeService.getListMyTeams(),
				select : null,
			};	

			vm.profileUpdate = {
				username:'',
				firstName:'',
				lastName: '',
				email:'',
				password: '',
				profil_password:'',
			};

			vm.nameTeamSelect = 'Not selected';
			vm.createTeamName = '';

			// update listViews of teams 
			vm.updateListTeams = function(){
				var list = homeService.getListMyTeams();
				vm.listTeams.teams = list;
			};

			// display creation of team
			vm.showCreateTeam = function(){
				vm.isCreated = true;
			};
			// hide creation of team
			vm.hideCreateTeam = function(){
				vm.isCreated = false;
			}

			// create a team
			vm.createTeam = function(){
				profileService.createTeam(vm.createTeamName);
				vm.isCreated = false;
				// wait for the server to be updated 
				$timeout( function(){
					profileService.getTeamsOfUser(homeService.getUserName());	
				}, 1000);
				$timeout( function(){
					vm.updateListTeams();
				}, 2000);
				
			};

			// Check if the user is logging
			// display or hide the profil views
			vm.cheklogin = function(){
				var token = homeService.getToken();
				if(token === ''){
					return false;
				}
				return true;
			};

			// initialized infos of views update
			vm.update = function(){
				var infos = homeService.getUserInfos();
				vm.profileUpdate.firstName = infos.firstName;
				vm.profileUpdate.lastName = infos.lastName;
				vm.profileUpdate.email = infos.email;
				vm.profileUpdate.username = infos.username;

				vm.showUpdate = true;
			};

			// select the team corresponding to the select input
			vm.selectTeam = function(){
				vm.listTeams.teams.forEach( (element) => {
					if(element.name === vm.nameTeamSelect){
						vm.listTeams.select = element;
					}
				});
			};

			// update account of user
			vm.putUpdate = function(){
				if(vm.profileUpdate.username != '' && 
				vm.profileUpdate.password != '' &&
				vm.profileUpdate.email != '' && 
				vm.profileUpdate != '' &&
				vm.profileUpdate != '' &&
				vm.profileUpdate.confirm_password === vm.profileUpdate.password){
					
					profileService.updateUser(
						homeService.getUserName(), {
						email: vm.profileUpdate.email,
						firstName: vm.profileUpdate.firstName,
						lastName: vm.profileUpdate.lastName,
						password: vm.profileUpdate.password,
						});
				}
			};

			// hide the update view
			vm.cancelUpdate = function(){
				vm.showUpdate = false;
			};
	}

})();
