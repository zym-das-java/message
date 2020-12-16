var app = angular.module('IM',['pagination']);
app.controller('IMController' ,function($scope,Service,$http){
    $scope.entity = {
    }
    $scope.aaaa={
        currentPage: 1,  				//当前页
        totalItems: 10,					//总记录数
        itemsPerPage: 10,				//每页记录数
        perPageOptions: [10, 20, 30, 40, 50], //分页选项，下拉选择一页多少条记录
        onChange: function () {			//页面变更后触发的方法
            $scope.reloadList();		//启动就会调用分页组件
        }
    }
    $scope.reloadList = function () {
        var entity = $scope.entity
        entity.pageNum=$scope.aaaa.currentPage,
        entity.pageSize=$scope.aaaa.itemsPerPage
        console.log(entity)
        Service.search(entity).success(
            function (response) {
                $scope.listData = response;
                $scope.aaaa.totalItems = response.total;
            }
        )
    }
    $scope.getSampleOne = function(id){
        window.location.href = "editIM.html?id="+id;
    }


    //读取列表数据绑定到表单中
    $scope.changeCity = function(){

        var code = $scope.entity.province
        Service.changeCity(code).success(
            function(response){
                $scope.citys=response;
                $scope.countys=[];
            }
        );
    }
    $scope.changeCounty = function(){
        var code = $scope.entity.city
        Service.changeCounty(code).success(
            function (response) {
                $scope.countys = response;
            }
        )
    }
    $scope.cropSpecies = function(){
        var id = $scope.entity.cropCategoryId
        Service.CropCategory(id).success(
            function (response) {
                $scope.CropSpecies = response;
            }
        )
    }
    $http({
        url:"/find/findAllCropSpecies",
        method:"get"
    }).success(function (data) {
        $scope.ListCropSpecies = data;
    })

    $http({
        url:"/find/findProvince",
        method:"get"
    }).success(function (data) {
        $scope.provinces = data;
    })

    $http({
        url:"/find/findAllCropCategory",
        method:"get"
    }).success(function (data) {
        $scope.CropCategorys = data;
    })
    $http({
        url:"/find/findToxinType",
        method:"get"
    }).success(function (data) {
        $scope.SampleToxinInfos = data;
    })

});
