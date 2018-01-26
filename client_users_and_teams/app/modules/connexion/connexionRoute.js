'use strict';

/**
 * @ngdoc function
 * @name app.route:connexionRoute
 * @description
 * # connexionRoute
 * Route of the app
 */

angular.module('connexion')
	.config(['$stateProvider', function ($stateProvider) {
		
		$stateProvider
			.state('home.connexion', {
				url:'/connexion',
				templateUrl: 'app/modules/connexion/connexion.html',
				controller: 'ConnexionCtrl',
				controllerAs: 'vm'
			});

		
	}]);
