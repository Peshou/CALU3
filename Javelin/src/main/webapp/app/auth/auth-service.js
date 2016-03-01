/**
 * Created by Stefan on 2/27/2016.
 */
AngularApp.factory('Auth', [
    '$rootScope',
    '$state',
    '$q',
    'Principal',
    'AuthServerProvider',
    'Account',
    'Register',
    'Password',
    function ($rootScope, $state, $q, Principal, AuthServerProvider, Account, Register, Password) {
        return {
            login: function (credentials, callback) {
                var cb = callback || angular.noop;
                var deferred = $q.defer();
                AuthServerProvider.login(credentials)
                    .then(function (data) {
                        Principal.identity(true).then(function (account) {
                            deferred.resolve(data);
                        });
                        return cb();
                    }).catch(function (err) {
                    this.logout();
                    deferred.reject(err);
                    return cb(err);
                }.bind(this));

                return deferred.promise;
            },
            logout: function () {
                AuthServerProvider.logout();
                Principal.authenticate(null);
                $rootScope.previousStateName = undefined;
                $rootScope.previousStateNameParams = undefined;
            },
            createAccount: function (account, callback) {
                var cb = callback || angular.noop;
                return Register.save(account, function () {
                        return cb(account);
                    },
                    function (err) {
                        this.logout();
                        return cb(err);
                    }.bind(this)).$promise;
            },
            updateAccount: function (account, callback) {
                var cb = callback || angular.noop;
                return Account.save(account, function () {
                    return cb(account);
                }, function (err) {
                    return cb(err);
                }.bind(this)).$promise;
            },
            changePassword: function (passwords, callback) {
                var cb = callback || angular.noop;

                return Password.save(passwords, function () {
                    return cb();
                }, function (err) {
                    console.log(err);
                    return cb(err);
                }).$promise;
            },
            authorize: function (force) {
                return Principal.identity(force).then(function () {
                    var isAuthenticated = Principal.isAuthenticated();
                    if (isAuthenticated && $rootScope.toState.parent === 'account' && ($rootScope.toState.name === 'login' || $rootScope.toState.name === 'register')) {
                        $state.go('home');
                    }
                    if ($rootScope.toState.data.authorities && $rootScope.toState.data.authorities.length > 0 && !Principal.hasAnyAuthority($rootScope.toState.data.authorities)) {
                        if (isAuthenticated) {
                            $state.go('accessdenied');
                        }
                        else {
                            $rootScope.previousStateName = $rootScope.toState;
                            $rootScope.previousStateNameParams = $rootScope.toStateParams;

                            $state.go('login');
                        }
                    }
                });
            }

        };
    }]);