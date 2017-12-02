var crudApp = angular.module('crudApp', ['ui.bootstrap','ngSanitize','ngCsv']);

crudApp.filter('startFrom', function() {
    return function(input, start) {
        if(input) {
            start = +start; //parse to int
            return input.slice(start);
        }
        return [];
    }
});


crudApp.controller("DbController", ['$scope', '$http', function ($scope, $http) {
	// Function to get employee details from the database
    getInfo();
    function getInfo() {
        // Sending request to EmpDetails.php files 
        $http.post('databaseFiles/empDetails.php').success(function(data){
        $scope.list = data;
        $scope.currentPage = 1; //current page
        $scope.entryLimit = 100; //max no of items to display in a page
        $scope.filteredItems = $scope.list.length; //Initially for no filter  
        $scope.totalItems = $scope.list.length;
    });
    $scope.setPage = function(pageNo) {
        $scope.currentPage = pageNo;
    };
    $scope.filter = function() {
        $timeout(function() { 
            $scope.filteredItems = $scope.filtered.length;
        }, 10);
    };
    $scope.sort_by = function(predicate) {
        $scope.predicate = predicate;
        $scope.reverse = !$scope.reverse;
    };
    }
    
	// Enabling show_form variable to enable Add employee button
	$scope.show_form = true;
	// Function to add toggle behaviour to form
	$scope.formToggle = function () {
		$('#empForm').slideToggle();
		$('#editForm').css('display', 'none');
	}
	$scope.insertInfo = function (info) {
		$http.post('databaseFiles/insertDetails.php', {
			"div_code": info.div_code, "schm_code": info.schm_code,
			"acc_no": info.acc_no, "emitra_key": info.emitra_key, "acc_no_old": info.acc_no_old, "ser_no": info.ser_no,
			"name": info.name, "add1": info.add1, "add2": info.add2, "city": info.city, "mtr_no": info.mtr_no,
			"mtr_size": info.mtr_size, "mtr_owner": info.mtr_owner, "c_type": info.c_type, "sline_cd": info.sline_cd,
	"avg": info.avg, "flat_rate": info.flat_rate, "last_read": info.last_read, "curr_read": info.curr_read
		}).success(function (data) {
			if (data == true) {
				getInfo();
				$('#empForm').css('display', 'none');
			}
		});
	}
	$scope.deleteInfo = function (info) {
		$http.post('databaseFiles/deleteDetails.php', { "acc_no": info.acc_no }).success(function (data) {
			if (data == true) {
				getInfo();
			}
		});
	}
	$scope.currentUser = {};
	$scope.editInfo = function (info) {
		$scope.currentUser = info;
		$('#empForm').slideUp();
		$('#editForm').slideToggle();
	}
	$scope.UpdateInfo = function (info) {
		$http.post('databaseFiles/updateDetails.php', {
			"div_code": info.div_code, "schm_code": info.schm_code,
			"acc_no": info.acc_no, "emitra_key": info.emitra_key, "acc_no_old": info.acc_no_old, "ser_no": info.ser_no,
			"name": info.name, "add1": info.add1, "add2": info.add2, "city": info.city, "mtr_no": info.mtr_no,
			"mtr_size": info.mtr_size, "mtr_owner": info.mtr_owner, "c_type": info.c_type, "sline_cd": info.sline_cd,
	"avg": info.avg, "flat_rate": info.flat_rate, "last_read": info.last_read, "curr_read": info.curr_read
		}).success(function (data) {
			$scope.show_form = true;
			if (data == true) {
				getInfo();
			}
		});
	}
	$scope.updateMsg = function (acc_no) {
		$('#editForm').css('display', 'none');
	}
}]);