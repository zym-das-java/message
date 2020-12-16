var app = angular.module('UpData',[]);
app.controller('UpDataController' ,function($scope,Service,$http,$compile){
    var id =location.href.split("id=")[1];
    //保存
    $scope.entity={
        harvestTime:'',
        samplingTime:'',
        sampleToxinList:[],
        id:id
    }
    $scope.upSample=function(){
        var arr =$("#dt").val().split("-")
        if(arr[1].length==1){
            arr[1]='0'+arr[1]
        }
        if (arr[2].length==1){
            arr[2]='0'+arr[2]
        }
        $scope.entity.harvestTime=arr[0]+'-'+arr[1]+'-'+arr[2]


        var arr2 =$("#dt-b").val().split("-")
        if(arr2[1].length==1){
            arr2[1]='0'+arr2[1]
        }
        if (arr2[2].length==1){
            arr2[2]='0'+arr2[2]
        }
        $scope.entity.samplingTime=arr2[0]+'-'+arr2[1]+'-'+arr2[2]


        var list = [];
        $.each($(".edittable-middle"), function (index, item) {
            list.push({
                toxinId: $(item).find("select[name='toxin_id']").val(),
                toxinCount: $(item).find("input[name='toxin_count']").val()
            })
        });
        $scope.entity.sampleToxinList=list
        var entity = $scope.entity;
        console.log(entity)
        Service.upSample(entity).success(
            function (response) {
                if(response.success){
                    //重新查询
                    alert(response.message);
                    window.location.href="../views/IM.html";//重新加载
                }else{
                    alert(response.message);
                }
            }
        )
    }
        $scope.append
        $scope.index = 0;
        $scope.addAdds = function () {
            var model = "toxin" + $scope.index;
            var model1 = "toxinCount" + $scope.index;
                 append = '<li class="edittable-middle"ng-class="edittable-middle" >\n' +
                '<div class="editmiddle-left">\n' +
                '毒素信息\n' +
                '</div>\n' +
                '<div class="editmiddle-right">\n' +
                '<ul>\n' +
                '<li>\n' +
                '<lable>毒素种类</lable>\n' +
                '<select ng-model="'+'entity.sampleToxinList'+'.'+ model + '" ng-class="toxinId" name="toxin_id" id="toxin_id">\n' +
                '<option value="">请选择</option>\n' +
                '<option ng-selected="SampleToxinInfo.id==entity.sampleToxinList'+'.'+ model + '" ng-repeat="SampleToxinInfo in SampleToxinInfos"ng-value="SampleToxinInfo.id">{{SampleToxinInfo.toxinType}}</option>\n' +
                '</select>\n' +
                '<span id="toxin_id"></span>\n' +
                '</li>\n' +
                '<li>\n' +
                '<lable>毒素含量</lable>\n' +
                '<input ng-class="toxinCount" ng-model="'+'entity.sampleToxinList'+'.'+ model1 + '" name="toxin_count" maxlength="10" placeholder="毒素含量最大可输入十位" type="text">\n' +
                '<span id="toxin_count"></span>\n' +
                '</li>\n' +
                '<li>\n' +
                '<a class="del">删除</a>\n' +
                '</li>\n' +
                '</ul>\n' +
                '</div>\n' +
                '</li>'
            var ele = $compile(append)($scope);
            $(".addds").append(ele);
            $scope.index = $scope.index + 1;
        }



        //读取列表数据绑定到表单中
        $scope.changeCity = function () {
            var code = $scope.entity.province
            Service.changeCity(code).success(
                function (response) {
                    $scope.citys = response;
                }
            );
        }
        $scope.changeCounty = function () {
            var code = $scope.entity.city
            Service.changeCounty(code).success(
                function (response) {
                    $scope.countys = response;
                }
            )
        }
        $scope.cropSpecies = function () {
            var id = $scope.entity.cropCategoryId
            Service.CropCategory(id).success(
                function (response) {
                    $scope.CropSpecies = response;
                }
            )
        }



        $http({
            url: "/find/findProvince",
            method: "get"
        }).success(function (data) {
            $scope.provinces = data;
        })
        $http({
            url: "/find/findAllCropCategory",
            method: "get"
        }).success(function (data) {
            $scope.CropCategorys = data;
        })
        $http({
            url: "/find/findToxinType",
            method: "get"
        }).success(function (data) {
            $scope.SampleToxinInfos = data;
        })
    $scope.entity={
        sampleId:'', cropCategoryId:'', breed:'',
        province:'', city:'', county:'', township:'',
        village:'', household:'', harvestTime:'',
        samplingTime:'', samplingPeople:'', seasonal:'',
        description:'', xpollutionRate:''

    }
    $scope.entity.sampleToxinList = {
    }
    $http({
        url:"../find/findbyidupdata?id="+id,
        method:"post"
    }).success(function (data) {
        $scope.entity.sampleId = data.sampleId
        $scope.entity.cropCategoryId = data.cropCategoryId
        $scope.cropSpecies()
        $scope.entity.breed = data.breed
        $scope.entity.province = data.province
        $scope.changeCity()
        $scope.entity.city = data.city
        $scope.changeCounty()
        $scope.entity.county = data.county
        $scope.entity.township = data.township
        $scope.entity.village = data.village
        $scope.entity.household = data.household
        $scope.entity.harvestTime = data.harvestTime
        $scope.entity.samplingTime = data.samplingTime
        $scope.entity.samplingPeople = data.samplingPeople
        $scope.entity.seasonal = data.seasonal
        $scope.entity.description = data.description
        $scope.entity.pollutionRate = data.pollutionRate
        $scope.entity.id = data.id

        $.each(data.sampleToxinList, function (index, item) {
            $scope.addAdds()
            $scope.entity.sampleToxinList["toxin" + index] = item.toxinId
            $scope.entity.sampleToxinList["toxinCount" + index] = item.toxinCount
        })
    })
})
