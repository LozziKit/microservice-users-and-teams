(function() {
	'use strict';

	/**
	 * @ngdoc index
	 * @name app
	 * @description
	 * # app
	 *
	 * Main modules of the application.
	 */

	angular.module('users-and-teams', [
		'ngResource',
		'ngAria',
		'ui.bootstrap',
		'ngMaterial',
		'ngMdIcons',
		'ngCookies',
		'ngAnimate',
		'ngMessages',
		'ngTouch',
		'ngSanitize',
		'ui.router',
		'home',
		'profile',
		'connexion',
	]);

})();
