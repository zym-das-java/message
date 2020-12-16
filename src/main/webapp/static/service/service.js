app.service('Service',function($http){
    
    this.search = function (entity) {
        return  $http({
            url:'../find/findAll',
            method:'post',
            params: entity
        });

    }
    this.add=function(entity){
        return $http.post('../find/add',entity);
    }
    this.upSample=function(entity){
        return $http.post('../find/upSampleInfo',entity);
    }
    this.changeCity=function(code){
        return $http.get('../find/findCity?code='+code);
    }
    this.changeCounty = function (code) {
        return $http.get('../find/findTown?code='+code);
    }
    this.CropCategory = function (id) {
        return $http.get('../find/selectByCropCategoryId?id='+id);
    }

});
