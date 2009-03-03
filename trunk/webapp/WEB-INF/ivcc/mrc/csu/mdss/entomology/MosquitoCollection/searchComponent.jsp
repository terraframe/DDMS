<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<mjl:form name="ivcc.mrc.csu.mdss.entomology.MosquitoCollection.search" method="POST">
  <dl>
    <dt> Geo Id </dt>
    <dd> <mjl:input param="geoId" type="text" /></dd>
    <dt> Date </dt>
    <dd> <mjl:input param="collectionDate" type="text" classes="DatePick" id="collectionDate"/></dd>
  </dl>  
  <mjl:command action="ivcc.mrc.csu.mdss.entomology.MosquitoCollectionController.searchByGeoIdAndDate" name="search.button"
  value="Search Non-AJAX"
  />
</mjl:form>

<div id="cal1Container" class="yui-skin-sam"></div>

<style>
#example {height:30em;}
label { display:block;float:left;width:45%;clear:left; }
.clear { clear:both; }
#resp { margin:10px;padding:5px;border:1px solid #ccc;background:#fff;}
#resp li { font-family:monospace }
</style>

<script>
YAHOO.namespace("example.container");

function init() {
	
	// Define various event handlers for Dialog
	var handleSubmit = function() {
		this.submit();
	};
	var handleCancel = function() {
		this.cancel();
	};
	var handleSuccess = function(o) {
		var response = o.responseText;
		//response = response.split("<!")[0];
		document.getElementById("resp").innerHTML = response;
	};
	var handleFailure = function(o) {
		alert("Submission failed: " + o.status);
	};

	// Instantiate the Dialog
	YAHOO.example.container.dialog1 = new YAHOO.widget.Dialog("dialog1", 
							{ width : "30em",
							  fixedcenter : true,
							  visible : false, 
							  constraintoviewport : true,
							  buttons : [ { text:"Submit", handler:handleSubmit, isDefault:true },
								      { text:"Cancel", handler:handleCancel } ]
							});

	// Wire up the success and failure handlers
	YAHOO.example.container.dialog1.callback = { success: handleSuccess,
						     failure: handleFailure };
	
	// Render the Dialog
	YAHOO.example.container.dialog1.render();

	YAHOO.util.Event.addListener("show", "click", YAHOO.example.container.dialog1.show, YAHOO.example.container.dialog1, true);
	YAHOO.util.Event.addListener("hide", "click", YAHOO.example.container.dialog1.hide, YAHOO.example.container.dialog1, true);
}

YAHOO.util.Event.onDOMReady(init);
</script>

<div>
<button id="show">Search AJAX</button> 
</div>

<div id="dialog1">
<div class="hd">No Mosquito Collection Found, Would you like to create a new one?</div>
<div class="bd">
<form method="POST" action="ivcc.mrc.csu.mdss.entomology.MosquitoCollectionController.create.mojax" name="ivcc.mrc.csu.mdss.entomology.MosquitoCollection.form.name" id="ivcc.mrc.csu.mdss.entomology.MosquitoCollection.form.id" >

  <input type="hidden" name="dto.componentId" value="qxrz86c6941fz4p8ob1t9nhnfy40k9csz8x53mzcadevs433ilux13buxvfnvn1f" />

<input type="hidden" name="dto.isNew" value="true" />

    <dl>
      <dt>
        <label>
          Collection Method
        </label>
      </dt>
      <dd>
        <select name="dto.collectionMethod">

          <option value="0bd7htrkvsj365ehyldk79az5r7g5fypxwux31dj5itcrmk8s7t50bkbioks4fo8">
            collection of dead specimens
          </option>

        
          <option value="0ozgl9vxdw543nap0yrkmvhhf311lexkxwux31dj5itcrmk8s7t50bkbioks4fo8">
            collection of eggs
          </option>

        
          <option value="0smvqg038gw8shf61ds7w42iklajlyr8xwux31dj5itcrmk8s7t50bkbioks4fo8">
            dead on ground catch
          </option>

        
          <option value="24sa38pts0ypsbmdbpljsjebzkuwslcvxwux31dj5itcrmk8s7t50bkbioks4fo8">
            field population catch
          </option>

        
          <option value="2872r2wt35sfaowmqagl88exhc2i4a5oxwux31dj5itcrmk8s7t50bkbioks4fo8">
            catch of live specimens
          </option>

        
          <option value="6s73meb4sxdfohdyrjoickbt8vzkpl7fxwux31dj5itcrmk8s7t50bkbioks4fo8">
            terrain environment catch
          </option>

        
          <option value="8n96wgpozfvwctpmqgwmfw34c2ayvcwmxwux31dj5itcrmk8s7t50bkbioks4fo8">
            collection of pupae
          </option>

        
          <option value="qplg4a4dy243f6vm6simisdjcuu9a09cxwux31dj5itcrmk8s7t50bkbioks4fo8">
            collection of larvae
          </option>

        
          <option value="qycoxtfpcdivw9bkyu891svhrjn0fakexwux31dj5itcrmk8s7t50bkbioks4fo8">
            aquatic environment catch
          </option>

        
          <option value="r1lw9xdb2tkvg1ju9lzsetcjggcywco0xwux31dj5itcrmk8s7t50bkbioks4fo8">
            collection of eggs via scraping
          </option>

        
          <option value="t080tw2r4qt1xpulp15a7xnhjza752cwxwux31dj5itcrmk8s7t50bkbioks4fo8">
            collection of naturally deposited eggs
          </option>

        
          <option value="xtlt9jdfgg5rln12tw7dk51q6llgdn7dxwux31dj5itcrmk8s7t50bkbioks4fo8">
            collection of adults
          </option>

        </select>

      </dd>
      <dt>
        <label>
          Date Collected
        </label>
      </dt>
      <dd>

        <input type="text" name="dto.dateCollected" value="" id="dto.dateCollected.id" class="DatePick"/>

        
      </dd>
      <dt>
        <label>
          Geo Entity
        </label>
      </dt>
      <dd>
        <select name="dto.geoEntity">

          <option value="1t7rlmhmiha608qk3a9ojirekt4i998csl2l2yltz5t2v5kkufj6329ibwi8sl0a">
            MOON1
          </option>

        </select>

      </dd>
    </dl>
 
</form>



</div>
</div>

<div id="resp"></div>	
