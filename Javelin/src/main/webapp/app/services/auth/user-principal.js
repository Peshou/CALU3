//Helper class for storing the user's identity
//We needed some help with this so we found it answered
//http://stackoverflow.com/questions/22537311/angular-ui-router-login-authentication
//no need to reinvent the whole wheel
AngularApp.factory('Principal', ['$q', 'Account', function ($q, Account) {
    var _identity = undefined,
        _authenticated = false;
    return {
        isIdentityResolved: function () {
            return angular.isDefined(_identity);
        },
        isAuthenticated: function () {
            return _authenticated;
        },
        hasAuthority: function (authority) {
            if (!_authenticated) {
                return $q.when(false);
            }
            return this.identity().then(function (_id) {
                return _id.authorities && _id.authorities.indexOf(authority) !== -1;
            }, function (err) {
                return false;
            });
        },
        hasAnyAuthority: function (authorities) {
            if (!_authenticated || !_identity || !_identity.authorities) {
                return false;
            }

            for (var i = 0; i < authorities.length; i++) {
                if (_identity.authorities.indexOf(authorities[i]) !== -1) {
                    return true;
                }
            }

            return false;
        },
        authenticate: function (identity) {
            _identity = identity;
            _authenticated = identity !== null;
        },
        identity: function (force) {
            var deferred = $q.defer();
            if (force === true) {
                _identity = undefined;
            }

            if (angular.isDefined(_identity)) {
                deferred.resolve(_identity);
                return deferred.promise;
            }

            Account.get().$promise
                .then(function (account) {
                    _identity = account.data;
                    _authenticated = true;
                    deferred.resolve(_identity);
                })
                .catch(function () {
                    _identity = null;
                    _authenticated = false;
                    deferred.resolve(_identity);
                });
            return deferred.promise;
        }
    };
}]);