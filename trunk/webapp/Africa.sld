<?xml version="1.0" encoding="ISO-8859-1"?>
<StyledLayerDescriptor version="1.0.0" xmlns="http://www.opengis.net/sld" xmlns:ogc="http://www.opengis.net/ogc"
  xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://www.opengis.net/sld http://schemas.opengis.net/sld/1.0.0/StyledLayerDescriptor.xsd">
  <NamedLayer>
    <Name>mdss:district_view</Name>
    <UserStyle>
      <Title>Default polygon style</Title>
      <Abstract>A sample style that just draws out a solid gray interior with a black 1px outline</Abstract>
      <FeatureTypeStyle>
        <Rule>
          <Title>Polygon</Title>
          <PolygonSymbolizer>
            <Fill>
              <CssParameter name="fill">#FFFFFF</CssParameter>
            </Fill>
            <Stroke>
              <CssParameter name="stroke">#000000</CssParameter>
              <CssParameter name="stroke-width">1</CssParameter>
            </Stroke>
          </PolygonSymbolizer>
        </Rule>
        <Rule>
          <TextSymbolizer>
            <Label>
              <ogc:PropertyName>entityname_v</ogc:PropertyName>
            </Label>
            <Font>
              <CssParameter name="font-family">Times New Roman</CssParameter>
              <CssParameter name="font-style">Normal</CssParameter>
              <CssParameter name="font-size">14</CssParameter>
            </Font>
            <LabelPlacement>
              <PointPlacement>
                <AnchorPoint>
                  <AnchorPointX>0.5</AnchorPointX>
                  <AnchorPointY>0.5</AnchorPointY>
                </AnchorPoint>
              </PointPlacement>
            </LabelPlacement>
          </TextSymbolizer>
        </Rule>
      </FeatureTypeStyle>
    </UserStyle>
  </NamedLayer>
  <NamedLayer>
    <Name>terraframe:African_Rivers_4326</Name>
    <UserStyle>
      <Title>1 px blue line</Title>
      <Abstract>Default line style, 1 pixel wide blue</Abstract>
      <FeatureTypeStyle>
        <!--FeatureTypeName>Feature</FeatureTypeName-->
        <Rule>
          <Title>Blue Line</Title>
          <Abstract>A 1 pixel wide blue line</Abstract>
          <LineSymbolizer>
            <Stroke>
              <CssParameter name="stroke">#FF0033</CssParameter>
            </Stroke>
          </LineSymbolizer>
        </Rule>
      </FeatureTypeStyle>
    </UserStyle>
  </NamedLayer>
    <NamedLayer>

        <Name>mdss:mdsstest</Name>
        <UserStyle>

            <Title>MastenStyle</Title>
            <FeatureTypeStyle>

                <Rule>
                  <PointSymbolizer>
                        <Graphic>
                            <Mark>
                                <WellKnownName>circle</WellKnownName> <Stroke>
                                    <CssParameter name="stroke">#ff0000</CssParameter> <CssParameter name="width">4</CssParameter>
                                </Stroke>
                            </Mark> <Size>12</Size> <Rotation>0</Rotation>
                        </Graphic>
                    </PointSymbolizer>
                </Rule>

            </FeatureTypeStyle>

        </UserStyle>

    </NamedLayer>

</StyledLayerDescriptor>