        <!-- Import OpenLayers, reduced, wms read only version -->
        <script type="text/javascript">
            var map;
            var untiled;
            var tiled;
            var pureCoverage = false;
            // pink tile avoidance
            OpenLayers.IMAGE_RELOAD_ATTEMPTS = 5;
            // make OL compute scale according to WMS spec
            OpenLayers.DOTS_PER_INCH = 25.4 / 0.28;

            function init(){

                var bounds = new OpenLayers.Bounds(
                    36.718452, -17.700377000000003,
                    36.938452, -17.480376999999997
                );
                var options = {
                    controls: [],
                    maxExtent: bounds,
                    maxResolution: 0.000859375,
                    projection: "EPSG:4326",
                    units: 'degrees'
                };
                map = new OpenLayers.Map('map', options);

                // setup tiled layer
                tiled = new OpenLayers.Layer.WMS(
                    "mdss:mdsstest - Tiled", "http://127.0.0.1:8080/geoserver/wms",
                    {
                        width: '400',
                        srs: 'EPSG:4326',
                        layers: baseLayer,
                        height: '400',
                        styles: '',
                        format: 'image/png',
                        tiled: 'true',
                        tilesOrigin : "36.718452,-17.700377000000003"
                    },
                    {buffer: 0}
                );

                //map.addLayers([untiled, tiled]);
                map.addLayers([tiled]);

                // build up all controls
                map.addControl(new OpenLayers.Control.PanZoomBar({
                    position: new OpenLayers.Pixel(2, 15)
                }));
                map.addControl(new OpenLayers.Control.Navigation());
                map.addControl(new OpenLayers.Control.Scale($('scale')));
                map.addControl(new OpenLayers.Control.MousePosition({element: $('location')}));
                map.zoomToExtent(bounds);
            }

            init();
        </script>

        <div id="map"></div>