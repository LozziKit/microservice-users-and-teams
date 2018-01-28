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
			vm.isUpdating = false;
			vm.isTeamCreating = false;

			vm.token = homeService.getToken();
			vm.profile = homeService.getUserInfos();
			vm.listTeams = { 
				teams: homeService.getListMyTeams(),
				select : null,
			};	
			vm.listUsers ={
				users: homeService.getListUsers(),
				select : null,
			}
			vm.profileUpdate = {
				username:'',
				firstName:'',
				lastName: '',
				email:'',
				password: '',
				profil_password:'',
			};

			vm.nameTeamSelect = '';
			vm.createTeamName = '';
			vm.selectUserOnList = '';

			vm.isUpdatingOrCreating = function(){
				if(vm.isTeamCreating || vm.isUpdating){
					return true;
				}
				return false;
			}

			


			// update a team
			vm.updateTeam = function(){
				if(vm.listUsers.select != null){
					var alreadyOnList = false;
					vm.listTeams.select.members.forEach((element)=> {
						if(element.username == vm.listUsers.select.username){
							alreadyOnList = true;
						}
					});
					if(alreadyOnList == false){
						profileService.updateTeam(vm.listTeams.select, vm.listUsers.select);
					}
				}
			};

			// update listViews of teams 
			vm.updateListTeams = function(){
				var list = homeService.getListMyTeams();
				vm.listTeams.teams = list;
			};

			// display creation of team
			vm.showCreateTeam = function(){
				vm.isTeamCreating = true;
			};
			// hide creation of team
			vm.hideCreateTeam = function(){
				vm.isTeamCreating = false;
			}

			// create a team
			vm.createTeam = function(){
				profileService.createTeam(vm.createTeamName);
				vm.isTeamCreating = false;
				// wait for the server to be updated 
				$timeout( function(){
					profileService.getTeamsOfUser(homeService.getUserName());	
				}, 500);
				$timeout( function(){
					vm.updateListTeams();
				}, 600);
				
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

				vm.isUpdating = true;
			};

			// select the team corresponding to the select input
			vm.selectTeam = function(){
				vm.listTeams.teams.forEach( (element) => {
					if(element.name === vm.nameTeamSelect){
						vm.listTeams.select = element;
					}
				});
			};

			// Select the user for add on selected team
			vm.selectUser = function(){
				vm.listUsers.users.forEach((element) =>{
					if(element.username === vm.selectUserOnList){
						vm.listUsers.select = element;
					}
				});
			}

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
				vm.isUpdating = false;
			};

			// hide the update view
			vm.cancelUpdate = function(){
				vm.isUpdating = false;
			};
	}

})();
