/**
 * Created by Sopiyan on 25/05/2016.
 */
'use strict';
var aplikasiTravel = angular.module('aplikasiTravel', ['ngAnimate', 'ngTouch', 'angular-growl', 'smart-table', 'ui.bootstrap']);
aplikasiTravel.config(['growlProvider', function (growlProvider) {
    growlProvider.globalTimeToLive(5000);
    growlProvider.onlyUniqueMessages(true);
    growlProvider.globalPosition('bottom-right');
}]);

aplikasiTravel.controller('kotaController', ['$http', '$scope', 'growl', '$uibModal', function ($http, $scope, growl, $uibModal) {
    $scope.listKota = {};
    $scope.pesanerror = "";
    $scope.listTerminalKota = {};
    $scope.getListKota = function () {
        $http.get('/api/kota').then(sukses, gagal);
        function sukses(response) {
            $scope.listKota = response.data.content;
        };

        function gagal(response) {
            console.log(response)
            $scope.addSpecialWarnMessage = function () {
                growl.error("Ups Maaf, Terjadi Kesalahan Di Server STT");
            };
            $scope.addSpecialWarnMessage();
        };
    };
    $scope.getListKota();
    $scope.hapusKota = function (k) {
        $http.delete('/dashboard/kota/' + k.idKota).then(sukses, gagal);
        function sukses(response) {
            $scope.getListKota();
        };
        function gagal(response) {
            console.log(response);
            $scope.addSpecialWarnMessage = function () {
                growl.error("Ups Maaf, Terjadi Kesalahan Di Server STT");
            };
            $scope.addSpecialWarnMessage();
        };
    };
    $scope.simpanKota = function () {
        $http.post('/api/kota', $scope.kotaDto.namaKota).then(sukses, gagal);
        function sukses(response) {
            $scope.getListKota();
            $scope.kotaDto.namaTerminal = "";
            $scope.addSpecialWarnMessage = function () {
                growl.success("Kota Berhasil Disimpan");
            };
            $scope.addSpecialWarnMessage();
        };
        function gagal(response) {
            console.log(response);
            $scope.pesanerror = response.data.errors[0].defaultMessage;
            $scope.addSpecialWarnMessage = function () {
                growl.error($scope.pesanerror);
            };
            $scope.addSpecialWarnMessage();
        };
    };
    $scope.getListTerminalKota = function (x) {
        var result = {};
        $http.get('/dashboard/api/terminal/berdasarkan?kota=' + x).then(sukses, gagal);
        function sukses(response) {
            result = response.data.content;
        };
        function gagal(response) {
            console.log(response);
            $scope.pesanerror = response.data.errors[0].defaultMessage;
            $scope.addSpecialWarnMessage = function () {
                growl.error($scope.pesanerror);
            };
            $scope.addSpecialWarnMessage();
        };
    }

}]);
aplikasiTravel.controller('terminalController', ['$http', '$scope', 'growl', function ($http, $scope, growl) {
    $scope.listKota = {};
    $scope.terminalDto = {};
    $scope.pesanerror = "";
    $scope.listTerminal = {};
    $scope.enable = function () {
        $scope.disabled = false;
    };

    $scope.disable = function () {
        $scope.disabled = true;
    };

    $scope.clear = function () {
        $scope.kota.selected = undefined;
    };

    $scope.getListKota = function () {
        $http.get('/api/kota').then(sukses, gagal);
        function sukses(response) {
            $scope.listKota = response.data.content;
        };
        function gagal(response) {
            console.log(response);
        };
    };
    $scope.getListKota();
    $scope.simpanTerminal = function () {
        console.log($scope.kota[0]);
        $scope.terminalDto.idKota = $scope.kota[0];
        console.log($scope.terminalDto);
        $http.post('/dashboard/api/terminal', $scope.terminalDto).then(sukses, gagal);
        function sukses(response) {
            console.log($scope.terminalDto);
            $scope.terminalDto.namaTerminal = "";
            $scope.pesanerror = "";
            $scope.addSpecialWarnMessage = function () {
                growl.success("Berhasil");
            };
            $scope.addSpecialWarnMessage();
            $scope.getListTerminal();
            console.log($scope.kota[0]);
            console.log($scope.terminalDto);
        };
        function gagal(response) {
            $scope.pesanerror = response.data.errors[0].defaultMessage;
            $scope.addSpecialWarnMessage = function () {
                growl.error($scope.pesanerror);
            };
            $scope.addSpecialWarnMessage();
            $scope.kota = [];
        };


    };
    $scope.getListTerminal = function () {
        $http.get('/dashboard/api/terminal').then(sukses, gagal);
        function sukses(response) {
            $scope.pesanerror = "";
            $scope.listTerminal = response.data.content;
        };
        function gagal(response) {
            $scope.pesanerror = response.data.errors[0].defaultMessage;
            $scope.addSpecialWarnMessage = function () {
                growl.error($scope.pesanerror);
            };
            $scope.addSpecialWarnMessage();
        };
    };
    $scope.getListTerminal();
    $scope.hapusTerminal = function (x) {
        $http.delete('/dashboard/api/terminal/' + x.idTerminal).then(sukses, gagal);
        function sukses(response) {
            $scope.getListTerminal();
            $scope.addSpecialWarnMessage = function () {
                growl.success("Berhasil Di Hapus");
            };
            $scope.addSpecialWarnMessage();
            $scope.getListTerminal();
        };
        function gagal(response) {
            console.log(response);
            $scope.addSpecialWarnMessage = function () {
                growl.error("Ups Maaf, Terjadi Kesalahan Di Server STT");
            };
            $scope.addSpecialWarnMessage();
        };
    };


}]);
aplikasiTravel.controller('ruteController', ['$http', '$scope', 'growl', function ($http, $scope, growl) {
    $scope.listRute = {};
    $scope.pesanerror = "";
    $scope.tampilkanSemuaRute = function () {
        $http.get('/dashboard/api/rute').then(sukses, gagal);
        function sukses(response) {
            $scope.listRute = response.data.content;
            console.log($scope.listRute);
        };
        function gagal(response) {
            console.log(response);
            $scope.pesanerror = response.data.errors;
            $scope.addSpecialWarnMessage = function () {
                growl.error($scope.pesanerror);
            };
            $scope.addSpecialWarnMessage();
        };
    };
    $scope.tampilkanSemuaRute();
}]);
aplikasiTravel.controller('tiketController', ['$http', '$scope', 'growl', function ($http, $scope, growl) {
    $scope.listKota = {};
    $scope.kotaAsal ={};
    $scope.kotaTujuan={};
    $scope.listTerminalAsal = {};
    $scope.listTerminalTujuan = {};
    $scope.terminalAsal = {};
    $scope.terminalTujuan = {};
    $scope.step = 0;
    $scope.rute={};
    $scope.ruteDto = {};
    $scope.ruteTersedia = false;
    $scope.tanggal = new Date();
    $scope.asal={};
    $scope.namaKotaAsal={};
    $scope.namaKotaTujuan={};
    $scope.getListKota = function () {
        $http.get('/api/kota').then(sukses, gagal);
        function sukses(response) {
            $scope.listKota = response.data.content;
        };
        function gagal(response) {
            console.log(response)
            $scope.addSpecialWarnMessage = function () {
                growl.error("Ups Maaf, Terjadi Kesalahan Di Server STT");
            };
            $scope.addSpecialWarnMessage();
        };
    };
    $scope.getListKota();
    $scope.cariterminalAsal = function(){
        $http.get('/api/tiket/terminal?idKota='+ $scope.kotaAsal[0]).then(sukses,gagal);
        function sukses(response) {
            $scope.listTerminalAsal = response.data.content;
            if($scope.listTerminalAsal.length > 0){
                $scope.step = 1;
            }else{
                $scope.addSpecialWarnMessage = function () {
                    growl.error("Di Kota Ini Belum Tersedia Terminal");
                };
                $scope.addSpecialWarnMessage();
            }

        };
        function gagal(response) {
            console.log(response)
            $scope.addSpecialWarnMessage = function () {
                growl.error("Ups Maaf, Terjadi Kesalahan Di Server STT");
            };
            $scope.addSpecialWarnMessage();
        };
    };
    $scope.next = function(){
      $scope.step +=1;
    };
    $scope.cariterminalTujuan = function(){
        $http.get('/api/tiket/terminal?idKota='+$scope.kotaTujuan[0]).then(sukses,gagal);
        function sukses(response) {
            $scope.listTerminalTujuan = response.data.content;
            if($scope.listTerminalTujuan.length > 0){
                $scope.next();
            }else{
                $scope.addSpecialWarnMessage = function () {
                    growl.error("Di Kota Ini Belum Tersedia Terminal");
                };
                $scope.addSpecialWarnMessage();
            };
            $scope.next();
            };


        function gagal(response) {
            console.log(response)
            $scope.addSpecialWarnMessage = function () {
                growl.error("Ups Maaf, Terjadi Kesalahan Di Server STT");
            };
            $scope.addSpecialWarnMessage();
        };
    };
    $scope.prosesCariRute = function(){
        $http.get('/api/tiket/rute?terminalAsal='+ $scope.terminalAsal[0]+'&terminalTujuan='+$scope.terminalTujuan[0]).then(berhasil,gagal);
        function berhasil(response) {
            console.log(response.data);
            if(response.data.idRute == null){
                $scope.addSpecialWarnMessage = function () {
                    growl.error("Rute Belum Tersedia");
                };
                $scope.addSpecialWarnMessage();
                $scope.ruteTersedia = false;
            }else{
                $scope.ruteTersedia = true;
                $scope.rute = response.data;
                $scope.cariKotaAsal($scope.rute.terminalAsal.idTerminal);
                $scope.cariKotaTujuan($scope.rute.terminalTujuan.idTerminal);
            }
        };
        function gagal(response) {
            console.log(response);
            $scope.ruteTersedia = false;
            $scope.addSpecialWarnMessage = function () {
                growl.error("Ups Maaf, Terjadi Kesalahan Di Server STT");
            };
            $scope.addSpecialWarnMessage();
        };
    };
    $scope.cariKotaAsal = function(x){
      $http.get('/api/tiket/kota?idTerminal='+x).then(sukses,gagal);
        function sukses(respone){
            if(respone.data.idKota != null){
                $scope.namaKotaAsal= respone.data.namaKota;
            }
        };
        function gagal(response){
          console.log(response);
        };
    };
    $scope.cariKotaTujuan = function(x){
        $http.get('/api/tiket/kota?idTerminal='+x).then(sukses,gagal);
        function sukses(respone){
            if(respone.data.idKota != null){
                $scope.namaKotaTujuan= respone.data.namaKota;
            }
        };
        function gagal(response){
            console.log(response);
        };
    };

}]);

