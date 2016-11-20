      <!--BootStrap  -->
	var mymodal = angular.module('mymodal', []);
		
    mymodal.controller('appController', function($scope,$http){
        var url = "http://localhost:8080/Sac4U/rest/service/getapps";
        $http.get(url).success( function(response) {
            $scope.apps = response.payload.apps;
       	 });
     });
		
	mymodal.controller('MainCtrl', function ($scope) {
	    $scope.showModal = false;
	    $scope.toggleModal = function(app){
	    	$scope.app=app;
	    	$scope.title1 = app.app_name;
	        $scope.showModal = !$scope.showModal;
	    };
	 });
		
		mymodal.directive('modal', function () {
		    return {
		      template: '<div class="modal fade">' + 
		          '<div class="modal-dialog">' + 
		            '<div class="modal-content">' + 
		              '<div class="modal-header">' + 
		                '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' + 
		                '<img src="./img/apps/{{app.icon_path}}" width="35" height="35"/>'+
		                '<h4 class="modal-title" style="display:inline; font-weight:bold; margin-left:10px;">{{ title1 }}</h4>' + 
		              '</div>' + 
		              '<div class="modal-body" ng-transclude></div>' + 
		            '</div>' + 
		          '</div>' + 
		        '</div>',
		      restrict: 'E',
		      transclude: true,
		      replace:true,
		      scope:true,
		      link: function postLink(scope, element, attrs) {
		        scope.title = attrs.title;
		
		        scope.$watch(attrs.visible, function(value){
		          if(value == true)
		            $(element).modal('show');
		          else
		            $(element).modal('hide');
		        });
		
		        $(element).on('shown.bs.modal', function(){
		          scope.$apply(function(){
		            scope.$parent[attrs.visible] = true;
		          });
		        });
		
		        $(element).on('hidden.bs.modal', function(){
		          scope.$apply(function(){
		            scope.$parent[attrs.visible] = false;
		          });
		        });
		      }
		    };
		  });