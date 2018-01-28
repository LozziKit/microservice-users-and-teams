(function () {
	'use strict';

	/**
	* @ngdoc function
	* @name app.service:homeService
	* @description
	* # homeService
	* Service of the app
	* Contains the information of the connected user.
	*/

	angular.module('users-and-teams')
		.factory('homeService', homeService);

	homeService.$inject = [];

	function homeService() {

		// token of the connected user
		var token = '';

		// informations of the connected user
		var userInfos = {
			username: '',
			firstName:'',
			lastName:'',
			id:'',
			email:'',
		};

		// Status of all request on API 
		// can be get
		var status = {
			code : '',
			message : '',
			data: {},
		};

		// List of teams of the connected user
		var listMyTeams = [];

		var listUsers = [];
	
		/** Getters / Setters */
		function setListMyTeams(newList){
			listMyTeams = [];
			for(var i= 0; i < newList.length; i++){
				listMyTeams.push(newList[i]);
			}
		}
		function getListMyTeams(){
			return listMyTeams;
		}
		function setToken(newToken){
			token = newToken;
		}
		function getToken(){
			return token;
		}
		function getUserName(){
			return userInfos.username;
		}
		function setUserName(newUsername){
			userInfos.username = newUsername;
		}
		function setStatus(newStatus){
			status.code = newStatus.code;
			status.message = newStatus.message;
			status.data = newStatus.data;
		}
		function getStatus(){
			return status;
		}
		function setUserInfos(infos){
			userInfos.username = infos.username;
			userInfos.firstName = infos.firstName;
			userInfos.lastName = infos.lastName;
			userInfos.id = infos.id;
			userInfos.email = infos.email;
		}
		function getUserInfos(){
			return userInfos;
		}
		function setListUsers(newList){
			listUsers = [];
			for(var i= 0; i < newList.length; i++){
				listUsers.push(newList[i]);
			}
		}
		function getListUsers(){
			return listUsers;
		}
		
		// Display to the console the status of request API
		function checkStatus(code){
			if(status.code == code){
				console.log('successfuly');
			}else{
				console.log('failed');
			}
		}

		return {
			setStatus: setStatus,
			getStatus: getStatus,
			checkStatus: checkStatus,
			setUserInfos: setUserInfos,	
			getUserInfos: getUserInfos,	
			setToken: setToken,	
			getToken: getToken,
			getUserName: getUserName,
			setUserName: setUserName,
			getListMyTeams: getListMyTeams,
			setListMyTeams: setListMyTeams,
			setListUsers: setListUsers,
			getListUsers: getListUsers,
		};
	}

})();
