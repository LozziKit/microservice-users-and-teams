(function() {
	'use strict';

	/**
	* @ngdoc function
	* @name app.controller:connexionCtrl
	* @description
	* # connexionCtrl
	* Controller of the app
	*/

  	angular
		.module('users-and-teams')
		.controller('ConnexionCtrl', Connexion)
		.directive("compareTo", compareTo);

		Connexion.$inject = ['connexionService','homeService'];

		/*
		* recommend
		* Using function declarations
		* and bindable members up top.
		*/
		function compareTo () {
			return {
			  require: "ngModel",
			  scope: {
				  otherModelValue: "=compareTo"
			  },
			  link: function(scope, element, attributes, ngModel) {
		  
				  ngModel.$validators.compareTo = function(modelValue) {
					  return modelValue == scope.otherModelValue;
				  };
		  
				  scope.$watch("otherModelValue", function() {
					  ngModel.$validate();
				  });
			  }
			};
		  }

		function Connexion(connexionService, homeService) {
			var vm = this;

			/**
			 *  switch the view
			 *  for create an account => true
			 *  for sign-in => false
			 */
			vm.showNewAccount = false;

			// Select input for switch the view
			vm.account = {
				type : [
					{id: '0', value: 'new account'},
					{id: '1', value: 'login'},
				],
				select : {id: '1', value: 'login'}
			}

			// Save the new user infos
			vm.newUser = {
				username:'',
				password:'',
				email:'',
				firstName:'',
				lastName:'',
			};
			// Confirm password input
			vm.confirmPassword = '';

			// Save info for sign-in
			vm.user = {
				password: '',
				username :'',
			};

			// call for switch view 
			vm.switchView = function(){
				if(vm.account.select === 'new account'){
					vm.showNewAccount = true;
				}else{
					vm.showNewAccount = false;
				}
			};

			// call for sign-in 
			vm.signIn = function(){
				if(vm.user.username != '' && vm.user.password!= ''){
					connexionService.signIn(vm.user);
				}
			}

			vm.createAccount = function(){
				if(vm.newUser.username != '' && 
					vm.newUser.password != '' &&
					vm.newUser.email != '' && 
					vm.firstName != '' &&
					vm.lastName != '' &&
					vm.confirmPassword === vm.newUser.password){
					connexionService.createAccount(vm.newUser);	
				}
			}
		}
		 		
})();
