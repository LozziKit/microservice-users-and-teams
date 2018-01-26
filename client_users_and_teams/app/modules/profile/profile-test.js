(function () {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.test:profileTest
	 * @description
	 * # profileTest
	 * Test of the app
	 */

	describe('profile test', function () {
		var controller = null, $scope = null;

		beforeEach(function () {
			module('users-and-teams');
		});

		beforeEach(inject(function ($controller, $rootScope) {
			$scope = $rootScope.$new();
			controller = $controller('ProfileCtrl', {
				$scope: $scope
			});
		}));

		it('Should controller must be defined', function () {
			expect(controller).toBeDefined();
		});

	});
})();
