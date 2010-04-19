
/*
     Initialize and render the MenuBar when its elements are ready
     to be scripted.
 */

YAHOO.util.Event.onContentReady("mainNav", function () {

	/*
	 * Instantiate a MenuBar: The first argument passed to the constructor is the id
	 * for the Menu element to be created, the second is an object literal of
	 * configuration properties.
	 */

	var oMenuBar = new YAHOO.widget.MenuBar("mainNav", {
		autosubmenudisplay: true,
		hidedelay: 750,
		lazyload: true });

	/*
	 * Define an array of object literals, each containing the data necessary to
	 * create a submenu.
	 */

	var aSubmenuData = [
{
	id: "Administration",
	itemdata: [
	           { text: "Configure_System_Variables(109)", url: "dss.vector.solutions.PropertyController.viewAll.mojo" , visibleTo:'Administrator'},
	           { text: "Update_Country_Image", url: "dss.vector.solutions.PropertyController.editFlag.mojo" , visibleTo:'Administrator'},
	           { text: "Configure_Malaria_Season(127)", url: "dss.vector.solutions.general.MalariaSeasonController.viewAll.mojo" , visibleTo:'Administrator'},
	           { text: "Configure_Epi_Week(119)", url: "dss.vector.solutions.PropertyController.newInstance.mojo" , visibleTo:'Administrator'},
	           { text: "Enter_Population_Data(003)", url: "dss.vector.solutions.general.PopulationDataController.search.mojo", visibleTo:'Administrator'},
	           { text: "Alerts",
	        	   submenu: {
	        	   id: "Alerts",
	        	   itemdata: [
	        	              { text: "View_All_SystemAlert", url: "dss.vector.solutions.general.SystemAlertController.viewAll.mojo", visibleTo:'Administrator'},
	        	              { text: "View_All_EmailConfiguration", url: "dss.vector.solutions.general.EmailConfigurationController.viewAll.mojo", visibleTo:'Administrator'},
	        	              { text: "View_All_Email", url: "dss.vector.solutions.general.EmailController.viewAll.mojo", visibleTo:'Administrator'}
	        	              ]
	           }
	           },
	           { text: "User_Administration",
	        	   submenu: {
	        	   id: "User_Administration",
	        	   itemdata: [
	        	              { text: "Manage_People(114)", url: "dss.vector.solutions.PersonController.viewAll.mojo" , visibleTo:'Administrator'},
	        	              { text: "Edit_Visibility(117)", url: "dss.vector.solutions.util.ReadableAttributeController.getUniversal.mojo", visibleTo:'Administrator' },
	        	              { text: "Manage_Roles(144)", url: "dss.vector.solutions.RoleController.viewAll.mojo", visibleTo:'Administrator' }
	        	              ]
	           }
	           },
	           { text: "Synchronise",
	        	   submenu: {
	        	     id : "Synchronise", 
	        	     itemdata : [
	        	       { text: "Transaction_Log", url: "dss.vector.solutions.synchronization.TransactionController.viewRecordPage.mojo" , visibleTo:'Administrator'},
	        	       { text: "Import_Log", url: "dss.vector.solutions.synchronization.ImportController.viewLog.mojo" , visibleTo:'Administrator'}
	                 ]
	               }
	           },
	           { text: "Localization", disabled: false, url: "dss.vector.solutions.util.LocalizationController.selectLocales.mojo" , visibleTo:'Administrator'},
	           { text: "Ontology_Fields", url: "dss.vector.solutions.ontology.BrowserFieldController.viewAll.mojo", visibleTo: 'Administrator' },
	           { text: "Ontology_Admin", url: "dss.vector.solutions.ontology.TermController.viewTree.mojo", visibleTo: 'Administrator' }
	           ]
},
{
	id: "Case_Surveillance",
	itemdata: [
	           { text: "Aggregated_Cases",
	        	   submenu: {
	        	   id: "Aggregated_Cases",
	        	   itemdata: [               
	        	              { text: "Search_for_Aggregated_Cases(002)", url: "dss.vector.solutions.surveillance.AggregatedCaseController.search.mojo" , visibleTo:'Administrator'},
	        	              { text: "Query_Aggregated_Cases(129)", url: "dss.vector.solutions.query.QueryController.queryAggregatedCases.mojo" , visibleTo:'Administrator'}
	        	              ]
	           		}
	           },
	           { text: "Individual_Cases",
	        	   submenu: {
	        	   id: "Individual_Cases",
	        	   itemdata: [               
	        	              { text: "Enter_For_Individual_Cases(001)", url: "dss.vector.solutions.intervention.monitor.IndividualCaseController.newInstance.mojo" , visibleTo:'Administrator'},
	        	              { text: "Query_Individual_Cases(128)", url: "dss.vector.solutions.query.QueryController.queryIndividualCases.mojo" , visibleTo:'Administrator'}
	        	              ]
	           		}
	           },
	           { text: "Thresholds",
	        	   submenu: {
	        	   id: "Thresholds",
	        	   itemdata: [
	        	              { text: "Configure_Thresholds", url: "dss.vector.solutions.general.ThresholdDataController.editThresholdConfiguration.mojo", visibleTo:'Administrator'},
	        	              { text: "Enter_Thresholds(105)", url: "dss.vector.solutions.general.ThresholdDataController.search.mojo", visibleTo:'Administrator'}
	        	              ]
	           		}
	           },
               { text: "Configure_Case_Surveillance", url: "dss.vector.solutions.surveillance.AggregatedAgeGroupController.viewAll.mojo" , visibleTo:'Administrator'}
	           ]	           
},
{
	id: "Entomology_Surveillance",
	itemdata: [
	           {
	        	   text: "Mosquito_Collections",
	        	   submenu: {
	        	   id: "Mosquito_Collections",
	        	   itemdata: [
        		              { text: "Search_For_Collections(017)", url: "dss.vector.solutions.entomology.MosquitoCollectionController.search.mojo", visibleTo:'Administrator' },
	        		          { text: "Query_Mosquito_Collections(148)", url: "dss.vector.solutions.query.QueryController.queryMosquitoCollections.mojo" , visibleTo:'Administrator'},
	        	              ]
	           		}
	           },
	           {
	        	   text: "Collections_of_Immatures",
	        	   submenu: {
	        	   id: "Collections_of_Immatures",
	        	   itemdata: [
	        	              { text: "Immatures_Thresholds(DDSS903B)", url: "dss.vector.solutions.entomology.assay.EfficacyAssayController.viewAll.mojo" , visibleTo:'Administrator'},
	        	              { text: "Immatures_by_Container_Type(DDSS903)", url: "dss.vector.solutions.query.QueryController.queryEfficacyAssay.mojo" , visibleTo:'Administrator'},
	        	              { text: "Query_Immatures_by_Container_Type(DDSS904)", url: "dss.vector.solutions.query.QueryController.queryEfficacyAssay.mojo" , visibleTo:'Administrator'},
	        	              { text: "Pupae_by_Individual_Container(DDSS905)", url: "dss.vector.solutions.query.QueryController.queryEfficacyAssay.mojo" , visibleTo:'Administrator'},
	        	              { text: "Query_Pupae_by_Individual_Container(DDSS906)", url: "dss.vector.solutions.query.QueryController.queryEfficacyAssay.mojo" , visibleTo:'Administrator'}
	        	              ]
            		}
	           },
	           {
	        	   text: "Assays",
	        	   submenu: {
	        	   id: "Assays",
	        	   itemdata: [
	        	              { text: "Configure_Entomology_Variables", url: "dss.vector.solutions.entomology.ResistancePropertyController.viewAll.mojo", visibleTo:'Administrator' },
	        	              { text: "Manage_Insecticides", url: "dss.vector.solutions.general.InsecticideController.viewAll.mojo", visibleTo:'Administrator' },
	        	              { text: "Manage_Knock_Down_Properties(009)", url: "dss.vector.solutions.general.KnockDownTimePropertyController.search.mojo" , visibleTo:'Administrator'},
	        	              { text: "Manage_Lethal_Properties(009)", url: "dss.vector.solutions.general.LethalTimePropertyController.search.mojo" , visibleTo:'Administrator'},
	        	              ]
	          		}
	           },
	           ]
},
{
	id: "Intervention_Planning",
	itemdata: [
	           { text: "Planning_Calculator(DDSS911)", url: "dss.vector.solutions.intervention.monitor.SurveyPointController.viewAll.mojo" , visibleTo:'Administrator'},
               { text: "Configure_Application_Rate(106)", url: "dss.vector.solutions.irs.ApplicationRateController.view.mojo" , visibleTo:'Administrator'},
	           ]
},
{
	id: "Intervention_Monitoring",
	itemdata: [
	           { text: "Intervention_Control(DDSS907)", url: "dss.vector.solutions.intervention.monitor.SurveyPointController.viewAll.mojo" , visibleTo:'Administrator'},
               { text: "Query_Intervention_Control(DDSS909)", url: "dss.vector.solutions.irs.ApplicationRateController.view.mojo" , visibleTo:'Administrator'},
	           ]
},
{
	id: "StockControl",
	itemdata: [
	           { text: "Configure_Stock_Items(032)", url: "dss.vector.solutions.stock.StockItemController.viewAll.mojo", visibileTo:'Administrator'},
	           { text: "Manage_Stock(032)", url: "dss.vector.solutions.stock.StockEventController.search.mojo", visibileTo:'Administrator'},
	           { text: "Query_Stock", url: "dss.vector.solutions.query.QueryController.queryStock.mojo", visibileTo:'Administrator'}    	               	           
	           ]
},
{
	id: "GIS",
	itemdata: [
	           { text: "Configure_the_Universal_Tree(101)", url: "dss.vector.solutions.geo.GeoEntityTypeController.viewHierarchyTree.mojo?rootGeoHierarchyId=" , visibleTo:'Administrator'},
	           { text: "Manage_Geo_Entities", url: "dss.vector.solutions.geo.GeoEntityTreeController.displayTree.mojo?rootGeoEntityId=" , visibleTo:'Administrator'},
	           { text: "Generate_Maps", url: "dss.vector.solutions.query.MappingController.generateMaps.mojo", visibleTo:'Administrator'}

	           ]
},
];



//	Nifty recursive localizations and permisions!
	function localizeText(text_obj)
	{
		/* depth first search */
		for each(var obj in text_obj)
		{
			if(typeof obj == 'object')
			{
				localizeText(obj);
			}
		}

		/* do the localization */
		if(typeof text_obj.text == 'string')
		{
			// remove the use case number and localize
			var label = text_obj.text.split('(')[0];
			label = MDSS.localize(label);
			// add the use case number back in
			if(text_obj.text.split('(')[1])
			{
				//commenting out line below disable use case numbers
//				label += "<em class=\"helptext\">" + text_obj.text.split('(')[1].substring(0,3)  +"</em>";
			}
			text_obj.text = label;
		}

//		/*after depth first search then check permissions*/
//		if(typeof text_obj.visibleTo == 'string')
//		{
//		//check if the user is in any role that has access to this link
//		for each(role in MDSS.user.roles)
//		{
//		if(text_obj.visibleTo.indexOf(role) >= 0)
//		{
//		return(false);
//		}
//		}
//		/*if we make it here the user does not have access
//		text_obj.text = null;*/
//		delete text_obj.text;
//		}
	}

	localizeText(aSubmenuData);

	var ua = YAHOO.env.ua,
	oAnim;  // Animation instance


	/*
	 * "beforeshow" event handler for each submenu of the MenuBar instance, used to
	 * setup certain style properties before the menu is animated.
	 */

	function onSubmenuBeforeShow(p_sType, p_sArgs) {

		var oBody,
		oElement,
		oShadow,
		oUL;


		if (this.parent) {

			oElement = this.element;

			/*
			 * Get a reference to the Menu's shadow element and set its "height"
			 * property to "0px" to syncronize it with the height of the Menu
			 * instance.
			 */

			oShadow = oElement.lastChild;
			oShadow.style.height = "0px";


			/*
			 * Stop the Animation instance if it is currently animating a Menu.
			 */

			if (oAnim && oAnim.isAnimated()) {

				oAnim.stop();
				oAnim = null;

			}


			/*
			 * Set the body element's "overflow" property to "hidden" to clip the
			 * display of its negatively positioned <ul> element.
			 */

			oBody = this.body;


			// Check if the menu is a submenu of a submenu.

			if (this.parent &&
					!(this.parent instanceof YAHOO.widget.MenuBarItem)) {


				/*
				 * There is a bug in gecko-based browsers and Opera where an element
				 * whose "position" property is set to "absolute" and "overflow"
				 * property is set to "hidden" will not render at the correct width
				 * when its offsetParent's "position" property is also set to
				 * "absolute." It is possible to work around this bug by specifying
				 * a value for the width property in addition to overflow.
				 */

				if (ua.gecko || ua.opera) {

					oBody.style.width = oBody.clientWidth + "px";

				}


				/*
				 * Set a width on the submenu to prevent its width from growing when
				 * the animation is complete.
				 */

				if (ua.ie == 7) {

					oElement.style.width = oElement.clientWidth + "px";

				}

			}


			oBody.style.overflow = "hidden";


			/*
			 * Set the <ul> element's "marginTop" property to a negative value so
			 * that the Menu's height collapses.
			 */

			oUL = oBody.getElementsByTagName("ul")[0];

			oUL.style.marginTop = ("-" + oUL.offsetHeight + "px");

		}

	}


	/*
	 * "tween" event handler for the Anim instance, used to syncronize the size and
	 * position of the Menu instance's shadow and iframe shim (if it exists) with
	 * its changing height.
	 */

	function onTween(p_sType, p_aArgs, p_oShadow) {

		if (this.cfg.getProperty("iframe")) {

			this.syncIframe();

		}

		if (p_oShadow) {

			p_oShadow.style.height = this.element.offsetHeight + "px";

		}

	}


	/*
	 * "complete" event handler for the Anim instance, used to remove style
	 * properties that were animated so that the Menu instance can be displayed at
	 * its final height.
	 */

	function onAnimationComplete(p_sType, p_aArgs, p_oShadow) {

		var oBody = this.body,
		oUL = oBody.getElementsByTagName("ul")[0];

		if (p_oShadow) {

			p_oShadow.style.height = this.element.offsetHeight + "px";

		}


		oUL.style.marginTop = "";
		oBody.style.overflow = "";


		// Check if the menu is a submenu of a submenu.

		if (this.parent &&
				!(this.parent instanceof YAHOO.widget.MenuBarItem)) {


			// Clear widths set by the "beforeshow" event handler

			if (ua.gecko || ua.opera) {

				oBody.style.width = "";

			}

			if (ua.ie == 7) {

				this.element.style.width = "";

			}

		}

	}


	/*
	 * "show" event handler for each submenu of the MenuBar instance - used to kick
	 * off the animation of the <ul> element.
	 */

	function onSubmenuShow(p_sType, p_sArgs) {

		var oElement,
		oShadow,
		oUL;

		if (this.parent) {

			oElement = this.element;
			oShadow = oElement.lastChild;
			oUL = this.body.getElementsByTagName("ul")[0];


			/*
			 * Animate the <ul> element's "marginTop" style property to a value of
			 * 0.
			 */

			oAnim = new YAHOO.util.Anim(oUL,
					{ marginTop: { to: 0 } },
					.5, YAHOO.util.Easing.easeOut);


			oAnim.onStart.subscribe(function () {

				oShadow.style.height = "100%";

			});


			oAnim.animate();


			/*
			 * Subscribe to the Anim instance's "tween" event for IE to syncronize
			 * the size and position of a submenu's shadow and iframe shim (if it
			 * exists) with its changing height.
			 */

			if (YAHOO.env.ua.ie) {

				oShadow.style.height = oElement.offsetHeight + "px";


				/*
				 * Subscribe to the Anim instance's "tween" event, passing a
				 * reference Menu's shadow element and making the scope of the event
				 * listener the Menu instance.
				 */

				oAnim.onTween.subscribe(onTween, oShadow, this);

			}


			/*
			 * Subscribe to the Anim instance's "complete" event, passing a
			 * reference Menu's shadow element and making the scope of the event
			 * listener the Menu instance.
			 */

			oAnim.onComplete.subscribe(onAnimationComplete, oShadow, this);

		}

	}


	/*
	 * Subscribe to the "beforerender" event, adding a submenu to each of the items
	 * in the MenuBar instance.
	 */

	oMenuBar.subscribe("beforeRender", function () {

		var nSubmenus = aSubmenuData.length,
		i;


		if (this.getRoot() == this) {

			for (i = 0; i < nSubmenus; i++) {
				this.getItem(i).cfg.setProperty("submenu", aSubmenuData[i]);
			}

		}

	});


	/*
	 * Subscribe to the "beforeShow" and "show" events for each submenu of the
	 * MenuBar instance.
	 */

	oMenuBar.subscribe("beforeShow", onSubmenuBeforeShow);
	oMenuBar.subscribe("show", onSubmenuShow);


	/*
	 * Call the "render" method with no arguments since the markup for this MenuBar
	 * instance is already exists in the page.
	 */

	oMenuBar.render();

});