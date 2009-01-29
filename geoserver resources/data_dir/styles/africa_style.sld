<?xml version="1.0" encoding="UTF-8"?>
<StyledLayerDescriptor version="1.0.0"
	xsi:schemaLocation="http://www.opengis.net/sld StyledLayerDescriptor.xsd"
	xmlns="http://www.opengis.net/sld" xmlns:ogc="http://www.opengis.net/ogc"
	xmlns:xlink="http://www.w3.org/1999/xlink"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
<NamedLayer>
		<Name>africa</Name>
		<UserStyle>
			<Name>africa_style</Name>
			<Title>geoserver style</Title>
			<Abstract>Generated by GeoServer</Abstract>
			<FeatureTypeStyle>
			<Rule>
				<PolygonSymbolizer>
					<Fill>
						<CssParameter name="fill">#55CC55</CssParameter>
						<CssParameter name="fill-opacity">1</CssParameter>
					</Fill>
					<Stroke>
						<CssParameter name="stroke">#000000</CssParameter>
						<CssParameter name="stroke-opacity">1</CssParameter>
					</Stroke>
				</PolygonSymbolizer>
				<TextSymbolizer>
					<Label>
						<ogc:PropertyName>SUBJECT</ogc:PropertyName>
					</Label>
						<Font>
							<CssParameter name="font-family">Times New Roman</CssParameter>
							<CssParameter name="font-style">Normal</CssParameter>
							<CssParameter name="font-size">12</CssParameter>
						</Font>
					<Fill>
						<CssParameter name="fill">#EE00EE</CssParameter>
						<CssParameter name="fill-opacity">1</CssParameter>
					</Fill>
				</TextSymbolizer>
			</Rule>
			</FeatureTypeStyle>
		</UserStyle>
	</NamedLayer>
</StyledLayerDescriptor>