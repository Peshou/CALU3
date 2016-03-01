/**
 * Created by Stefan on 2/29/2016.
 */
AngularApp
    .config(['$stateProvider',function ($stateProvider) {
        $stateProvider
            .state('admin', {
                abstract: true,
                parent: 'site'
            });
    }]);
