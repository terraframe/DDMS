<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Ajax Example</title>

<script type="text/javascript" src="js/RunwaySDK.js"></script>
<script type="text/javascript">

  function createGeoEntity()
  {
    var geoEntity = new Mojo.$.mdss.test.GeoEntity();
    
    var geoId = new String(Math.random()).slice(-6);
    geoEntity.setGeoId(geoId);
    geoEntity.setEntityName("Some Entity Name");
    geoEntity.addTerrain(Mojo.$.mdss.test.Terrain.SENTINEL_SITE);
    
    var request = new Mojo.ClientRequest({
    
      // success handler for newly created mosquito
      onSuccess : function(newGeoEntity){
        var info = "New GeoEntity Created-- \n";
        info+= "Geo Id: "+newGeoEntity.getGeoId()+"\n";
        info += "Entity Name: "+newGeoEntity.getEntityName()+"\n";
        info += "Terrain: "+newGeoEntity.getTerrain()[0].name() +"\n";
        info += "toString: "+newGeoEntity.toString();
        
        alert(info);
      },
      
      // alert the exception message
      onFailure : function(e){
        alert(e.getLocalizedMessage());
      }
    });
    
    geoEntity.apply(request);
  }

  function onloadHandler(){
  
    // import the types needed to create a new GeoEntity
    var request = new Mojo.ClientRequest({
      onSuccess : function(){

        // import worked, so enable GeoEntity creation
        document.getElementById('imported').innerHTML = 'Success';
        var create = document.getElementById('create');
        create.disabled = '';
        create.addEventListener('click', createGeoEntity, false);
      },
      onFailure : function(){
        document.getElementById('imported').innerHTML = 'Failure';
      }
    });
    
    var options = {appendTo : document.getElementsByTagName('head')[0]};
    var types = ['mdss.test.GeoEntity', 'mdss.test.Terrain'];
    Mojo.importTypes(request, types, options);
  }

  window.addEventListener('load', onloadHandler , false);

</script>

</head>
<body>

Import Status: <span id="imported">Waiting</span><br />
<input type="button" disabled="disabled" id="create" value="Create GeoEntity" />
</body>
</html>