<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="geoEntity">
    <mjl:select param="geoEntity" items="${geoEntity}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="season">
    <mjl:select param="season" items="${season}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="target_0">
    <mjl:input param="target_0" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_1">
    <mjl:input param="target_1" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_10">
    <mjl:input param="target_10" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_11">
    <mjl:input param="target_11" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_12">
    <mjl:input param="target_12" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_13">
    <mjl:input param="target_13" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_14">
    <mjl:input param="target_14" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_15">
    <mjl:input param="target_15" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_16">
    <mjl:input param="target_16" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_17">
    <mjl:input param="target_17" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_18">
    <mjl:input param="target_18" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_19">
    <mjl:input param="target_19" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_2">
    <mjl:input param="target_2" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_20">
    <mjl:input param="target_20" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_21">
    <mjl:input param="target_21" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_22">
    <mjl:input param="target_22" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_23">
    <mjl:input param="target_23" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_24">
    <mjl:input param="target_24" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_25">
    <mjl:input param="target_25" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_26">
    <mjl:input param="target_26" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_27">
    <mjl:input param="target_27" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_28">
    <mjl:input param="target_28" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_29">
    <mjl:input param="target_29" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_3">
    <mjl:input param="target_3" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_30">
    <mjl:input param="target_30" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_31">
    <mjl:input param="target_31" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_32">
    <mjl:input param="target_32" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_33">
    <mjl:input param="target_33" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_34">
    <mjl:input param="target_34" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_35">
    <mjl:input param="target_35" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_36">
    <mjl:input param="target_36" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_37">
    <mjl:input param="target_37" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_38">
    <mjl:input param="target_38" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_39">
    <mjl:input param="target_39" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_4">
    <mjl:input param="target_4" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_40">
    <mjl:input param="target_40" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_41">
    <mjl:input param="target_41" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_42">
    <mjl:input param="target_42" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_43">
    <mjl:input param="target_43" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_44">
    <mjl:input param="target_44" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_45">
    <mjl:input param="target_45" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_46">
    <mjl:input param="target_46" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_47">
    <mjl:input param="target_47" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_48">
    <mjl:input param="target_48" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_49">
    <mjl:input param="target_49" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_5">
    <mjl:input param="target_5" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_50">
    <mjl:input param="target_50" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_51">
    <mjl:input param="target_51" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_52">
    <mjl:input param="target_52" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_6">
    <mjl:input param="target_6" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_7">
    <mjl:input param="target_7" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_8">
    <mjl:input param="target_8" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target_9">
    <mjl:input param="target_9" type="text" />
  </mjl:dt>
</mjl:component>
