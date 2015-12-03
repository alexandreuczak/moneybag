angular.module('app', ['ngRoute', 'ui.bootstrap']);

angular.module('app').config(function($routeProvider){
	$routeProvider
	.when('/', {
		templateUrl: 'listar.html',
		controller: 'listarPessoasCtrl'
	})
	.when('/listar',{
		redirectTo: '/'
	})
	.when('/adicionar',{
		templateUrl: 'add.html',
		controller: 'adicionarPessoaCtrl'
	})
	.when('/editar/:id',{
		templateUrl: 'edit.html',
		controller: 'editarPessoaCtrl'
	})
	.when('/visualizar/:id',{
		templateUrl: 'view.html',
		controller: 'visualizarPessoaCtrl'
	})
	.otherwise({redirectTo: '/'});
	
});



angular.module('app').controller('listarPessoasCtrl', function($scope, $http, $location, PessoaService, $modal){	
	$scope.remover = function (pessoa) {				
		$modal.open({
			templateUrl: 'confirm.html',
			controller: 'ModalConfirmCtrl',
			resolve: {
				pessoa: pessoa,
				callback: function (){
								return function () {
									PessoaService.listar(function(pessoas){
										$scope.pessoas = pessoas;	
									});	
					}
				}
			}
		});			
	};

	$scope.search = {
		text: ''
	};
	
	$scope.pessoas = [];
	
	PessoaService.listar(function(pessoas){
		$scope.pessoas = pessoas;	
	});				
});

angular.module('app').controller('ModalConfirmCtrl', function($scope, $modalInstance, pessoa, callback, PessoaService){
	$scope.pessoa = pessoa; 
	$scope.ok = function () {
		PessoaService.remover(pessoa, function(){
			callback();
			$modalInstance.close(true);
		});
		
	 };

	 $scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	 };
});

angular.module('app').controller('adicionarPessoaCtrl', function($scope, $http, $location, PessoaService){
	$scope.adicionar = function (pessoa){
		PessoaService.adicionar(pessoa, function(){
			$location.path('/listar');
		})
	};	
});

angular.module('app').controller('editarPessoaCtrl', function($scope, $routeParams, $location, PessoaService){
	$scope.pessoa = {
		id: $routeParams.id
	};
	
	PessoaService.buscar($scope.pessoa.id, function(data){
		$scope.pessoa = data;
	});
	
	$scope.atualizar = function(pessoa){
		PessoaService.atualizar(pessoa, function(){
			$location.path('/listar');
		})
	}
});

angular.module('app').controller('visualizarPessoaCtrl', function($scope, $routeParams, $location, PessoaService){
	$scope.pessoa = {
		id: $routeParams.id
	};
	
	PessoaService.buscar($scope.pessoa.id, function(data){
		$scope.pessoa = data;
	});
	
	$scope.remover = function (pessoa) {			
		PessoaService.remover(pessoa, function(){
			$location.path('/listar');
		});
	};
	
});


angular.module('app').service('PessoaService', function($http, config){
	return {
	
		listar: function(resolve){			
				$http.get(config.baseUrl + '/pessoas/listar').success(function(data){
					resolve(data.pessoas);
				}).error(function(data, status,header, config){
					console.log(status);
				});						
		},
		buscar: function(id, resolve){			
			$http.get(config.baseUrl + '/pessoas/buscar/' + id).success(function(data){
				resolve(data);
			}).error(function(data, status,header, config){
				console.log(status);
			});						
		},
		remover: function(pessoa, resolve){
			$http.post(config.baseUrl + '/pessoas/remover',pessoa).success(function(data, status,header, config){
				resolve();
			}).error(function(data, status,header, config){
				console.log(status);
			});
		},
		adicionar: function(pessoa, resolve){
			$http.post(config.baseUrl + '/pessoas/adicionar',pessoa).success(function(data, status,header, config){
				resolve();
			}).error(function(data, status,header, config){
				console.log(status);
			});	
		},
		atualizar: function(pessoa, resolve){
			$http.post(config.baseUrl + '/pessoas/atualizar',pessoa).success(function(data, status,header, config){
				resolve();
			}).error(function(data, status,header, config){
				console.log(status);
			});	
		}
	};
	
});

angular.module("app").value("config",{
	baseUrl: "http://localhost:8080/moneybag/rest"
});


