<jsp:include page="/WEB-INF/templates/header.jsp" />

<div id="gogogo">j</div>

<script type="text/javascript">
    var val = 'FF0000';
    var picker = new YAHOO.widget.ColorPicker("gogogo", {
      showcontrols: false,
      images: {
        PICKER_THUMB: "js/yui/build/colorpicker/assets/picker_thumb.png"
      },
      hex: val
    });

    picker.set('hex', val);
</script>

<jsp:include page="/WEB-INF/templates/footer.jsp" />