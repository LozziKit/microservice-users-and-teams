(function() {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.service:connexionService
	 * @description
	 * # connexionService
	 * Service of the app
	 */

  	angular
		.module('users-and-teams')
		.factory('connexionService', connexionService);
	
		connexionService.$inject = ['$http', 'profileService', 'homeService'];

		function connexionService ($http, profileService, homeService) {

			// Create a new user
			// Call POST api/users
			function createAccount(user){
				var req = {
					method: 'POST',
					url: 'http://localhost:8080/api/users',
					headers: {'Content-Type': 'application/json'},
					data: user
				};
				
				$http(req)
					.then(function (response) {
						var status = {code: response.status, message: response.statusText, data:{}};
						homeService.setStatus(status);
					}, function (error) {
						var status = {code: error.status, message: error.statusText, data:{}};
						homeService.setStatus(status);
					}
				);	
			};


			// User Authentication
			// Call POST sur api/auth
			function signIn(credentials){
				var req = {
					method: 'POST',
					url: 'http://localhost:8080/api/auth',
					headers: {		 
							'Accept': 'application/json, text/plain, */*'},
					data: credentials
				};

				$http(req)
					.then(function (response) {
						var status = {code: response.status, message: response.statusText, data:{}};
						homeService.setStatus(status);
						// Save informations of users
						homeService.setToken(response.data);
						homeService.setUserName(response.data.username);
						// Get inforamtions for profile
						profileService.getUser(credentials.username);
						profileService.getTeamsOfUser(credentials.username);
					}, function (error) {
						var status = {code: error.status, message: error.statusText, data:{}};
						homeService.setStatus(status);
						homeService.setToken('');
					}
				);
			};

			return {
				signIn: signIn,
				createAccount: createAccount,
			};
		}

})();
