var app = angular.module("myApp", ["ngResource","angularModalService"]);

app.controller("modController", function($scope) {
	var names = [ 'candidate1', 'emp1' ];
	$scope.names = names;
});

app.controller("newCandidateController",function($scope,ModalService){
	$scope.show = function(){
		
		ModalService.showModal({
			templateUrl:"modal.html",
			controller:"modalController"
		}).then(function(modal){
			modal.element.modal();
			modal.close.then(function(result){
				$scope.message="Your result "+result;
			});
		});
	};
});

app.controller("modalController",function($scope,close){
	$scope.close = function(result){
		close("result",500);
	};
});

app.controller("onGoingController", function($rootScope, $scope) {

	$scope.schedules = [ person1, person2 ];

	$scope.showCandidateDetails = function(candidate) {
		var id = candidate.candidateId;

		$(document).ready(function() {
			$(".candidateInfo").attr("class", "candidateInfo").show(function() {
				console.log("animation completed");
				$("#btn-controls").show(500);
			});

		});

		console.log("broadcasting candidateInfoClickEvent");
		$rootScope.$broadcast("candidateInfoClickEvent", candidate);

	};

});

app.controller("candidateInfoController", function($scope) {
	$scope.$on("candidateInfoClickEvent", function(event, data) {
		console.log(data);
		$scope.candidate = data;
	});
});

app.controller("upNextController", function($scope,scheduleService) {
	console.log("fetching schedule data..");
	$scope.val = "Hi";
	$scope.upNext = [];
	
	var content = scheduleService.getCompletedSchedule().then(
			function(data) {
				angular.forEach(data,function(val){
					console.log(val);
					$scope.upNext.push(val);
				});
			});
	
});

app.controller("showMsgController", function($q, $scope, $http,
		candidateInfoRequestService) {

	$scope.val = "hi";
	console.log("fetching data");
	var deferred = $q.defer();

	$scope.candidates=[];

	var content = candidateInfoRequestService.getAllCandidates().then(
			function(data) {
				angular.forEach(data,function(val){
					console.log(val);
					$scope.candidates.push(val);
				});
			});

	
});

app.service("candidateInfoRequestService", function($http) {
	console.log("service");
	this.getAllCandidates = function() {
		return $http.get(
				"http://localhost:8080/WebService/rest/candidate/all")
				.then(function(response) {
					return response.data;
				});
	};
});

app.service("scheduleService",function($http){
	
	console.log("scheduleService");
	
	this.getUpcomingSchedule = function(){
		return $http.get("http://localhost:8080/WebService/rest/schedule/all?q=upcoming")
		.then(function(response){
			return response.data;
		});
	},
	this.getCompletedSchedule = function(){
		//console.log("getting completed schedule");
		return $http.get("http://localhost:8080/WebService/rest/schedule/all?q=completed")
		.then(function(response){
			return response.data;
		});
	},
	this.getOngoingSchedule = function(){
		return $http.get("http://localhost:8080/WebService/rest/schedule/all?q=ongoing")
		.then(function(response){
			return response.data;
		});
	}
	
});


app.controller("completedController", function($scope) {

	$scope.schedules = [ person1, person2 ];

});

app.factory("CandidateFactory",function($resource){
	return $resource("http://localhost:8080/WebService/rest/candidate/all");
});

app.factory("ScheduleFactory",function($resource){
	return $resource("http://localhost:8080/WebService/rest/schedule/all");
});
