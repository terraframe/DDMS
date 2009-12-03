
MDSS.leaderSearch = function(config) {
  var searchEl = Mojo.Util.isString(config.search) ? document.getElementById(config.search) : config.search;
  var concreteEl = Mojo.Util.isString(config.concrete) ? document.getElementById(config.concrete) : config.concrete;

  var listFunction = function(valueObject) {
    var firstName = Mojo.$.dss.vector.solutions.PersonView.FIRSTNAME;
    var lastName = Mojo.$.dss.vector.solutions.PersonView.LASTNAME;
    var leaderId = Mojo.$.dss.vector.solutions.PersonView.LEADERID;

    return valueObject.getValue(firstName) + ' ' + valueObject.getValue(lastName) + ' - ' + valueObject.getValue(leaderId);
  };

  var idFunction = function(valueObject) {
    var id = Mojo.$.dss.vector.solutions.PersonView.ID;

    return valueObject.getValue(id);
  };

  var displayFunction = function(valueObject) {
    var firstName = Mojo.$.dss.vector.solutions.PersonView.FIRSTNAME;
    var lastName = Mojo.$.dss.vector.solutions.PersonView.LASTNAME;

    return valueObject.getValue(firstName) + ' ' + valueObject.getValue(lastName);
  };

  var searchFunction = Mojo.$.dss.vector.solutions.irs.SprayLeader.searchForLeader;

  var selectEventHandler = function() {};
   
  var search = new MDSS.GenericSearch(searchEl, concreteEl, listFunction, displayFunction, idFunction, searchFunction, selectEventHandler);
}