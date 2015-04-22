'use strict';

angular.module("snippetShare")
    .factory("Board", function BoardFactory($http){
        return {
            all: function(){
                return $http({method: "GET", url:"snippetshare/boards"});
            },
            create: function(board){
                return $http.post("snippetshare/boards", board, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(board){
                        return $.param(board);
                    }
                });
            },
            find: function (id) {
                return $http({method: "GET", url:"snippetshare/boards/" + id});
            },
            update: function (board, id) {
                return $http.post("snippetshare/boards/"+id, board, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(board){
                        return $.param(board);
                    }
                });
            },
            remove: function (id) {
                return $http({method: "DELETE", url:"snippetshare/boards/" + id});
            }

        }


    });
