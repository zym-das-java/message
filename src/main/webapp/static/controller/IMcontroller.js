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

    $scope.selectIds = [];  //初始化id数组的数据结构
    //选中/反选
    $scope.updateSelection = function ($event, id) {
        //判断复选框选中还是反选
        if ($event.target.checked) {
            $scope.selectIds.push(id);   //向数组添加元素
        } else {
            var index = $scope.selectIds.indexOf(id);  //返回元素的下标
            $scope.selectIds.splice(index, 1);    //数组移除元素  参数1：数组元素的下标 参数2：移除的个数
        }
        console.log($scope.selectIds)
    }

    $scope.Export=function(){
        var v = prompt("请输入文件名称");
        window.location.href = "/find/Export?ids="+$scope.selectIds + "&fileName="+v;
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
