Mojo.Meta.newClass('MDSS.GeoPickerWithUniversals', {

  IsAbstract : false,
  
  Extends : MDSS.GeoPicker,

  Instance : {

    /**
     * Constructor.
     */
    initialize : function()
    {
      this.$initialize(true);
    },
    
    layoutLeftPanel : function(parent)
    {
      var factory = com.runwaysdk.ui.Manager.getFactory();
      var dividerColor = "#87a4db";
      var unis = ["one", "two", "three"];
      
      // Universal grand daddy el
      var uniDaddy = factory.newElement("div", {"id":"uniDaddy" + this._suffix});
      uniDaddy.setStyle("margin-top", "10px");
      uniDaddy.setStyle("float", "left");
      uniDaddy.setStyle("width", "150px");
      uniDaddy.setStyle("height", "100%");
      parent.appendChild(uniDaddy);
      
      // Section title that says "Select columns"
      var uniSectionTitle = factory.newElement("h2", {"id":"uniSectionTitle" + this._suffix});
      uniSectionTitle.setInnerHTML("Select columns"); // TODO : localize
      uniDaddy.appendChild(uniSectionTitle);
      uniDaddy.appendChild(factory.newElement("hr", {"color": dividerColor}));
      
      // Universal tree list
      var uniListDiv = factory.newElement("div", {"id": "uniList" + this._suffix});
      var uniListDivJQ = $(uniListDiv.getRawEl());
      for (var i = 0; i < unis.length; ++i)
      {
        var uni = unis[i];
        
        var uniDiv = factory.newElement("div");
        uniListDiv.appendChild(uniDiv);
        
        var uniInput = factory.newElement("input", {"type": "checkbox"});
        uniInput.setStyle("margin-right", "10px");
        uniInput.setStyle("margin-left", "10px");
        uniDiv.appendChild(uniInput);
        
        var uniLabel = factory.newElement("span");
        uniLabel.setInnerHTML(uni);
        uniDiv.appendChild(uniLabel);
      }
      uniDaddy.appendChild(uniListDiv);
      
      
      parent.appendChild(factory.newElement("hr", {"width": "1", "size": "300"}, {"float": "left", "margin": "10px"}));
    },
    
    hasLeftPanel : function()
    {
      return true;
    }
  }
});