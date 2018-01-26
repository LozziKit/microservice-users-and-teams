'use strict';

/**
 * @ngdoc function
 * @name app.route:profileRoute
 * @description
 * # profileRoute
 * Route of the app
 */

angular.module('profile')
	.config(['$stateProvider', function ($stateProvider) {
		
		$stateProvider
			.state('home.profile', {
				url:'/profile',
				templateUrl: 'app/modules/profile/profile.html',
				controller: 'ProfileCtrl',
				controllerAs: 'vm'
			});

		
	}]);
