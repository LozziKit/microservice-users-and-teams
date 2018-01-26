(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.test:connexionTest
	 * @description
	 * # connexionTest
	 * Test of the app
	 */

	describe('connexion test', function () {
		var controller = null, $scope = null;

		beforeEach(function () {
			module('users-and-teams');
		});

		beforeEach(inject(function ($controller, $rootScope) {
			$scope = $rootScope.$new();
			controller = $controller('ConnexionCtrl', {
				$scope: $scope
			});
		}));

		it('Should controller must be defined', function () {
			expect(controller).toBeDefined();
		});

	});
})();
