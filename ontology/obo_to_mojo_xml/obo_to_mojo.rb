#use me like this:
# ruby obo_to_mojo.rb ident_methods.obo mdss.entomology.IdentificationMethod

#obo file must be ASCII, not unicode, and have unix style newlines


#id: MIRO:30000044
#name: field population catch
#namespace: mosquito_insecticide_resistance_ontology
#def: "Any population catch method using either aspiration or traps." [KL:KL]
#is_a: MIRO:20000000 ! method

#
#    <object
#      type="mdss.entomology.CollectionMethod"
#      id="NIGHT_BITING">
#      <valueAttribute
#        name="termName"
#        value="Night Biting" />
#    </object>

type = $*[1]
java_id = ''
miro_name = ''
value = ''
miro_id = ''
is_a_string = ''
is_a_id = ''
namespace = ''
miro_def = ''

out_buff = ''

used_ids = []

out_file = $*[0].gsub(/txt|obo/,'xml')

File.open($*[0] , "r").each_line do |line|
  array = line.strip.split(': ')
  case array[0]
    when 'id'
      miro_id = array[1].strip
      java_id = array[1].strip.upcase.gsub(/[^A-Z0-9]/,'_')
    when 'name'      
      miro_name = array[1]
    when 'namespace'
      namespace = array[1].strip
    when 'def'
      miro_def = array[1].strip.gsub('"','')
    when 'is_a'
      arr = array[1].split('!')
      is_a_id = arr[0].strip.upcase.gsub(/[^A-Z0-9]/,'_')
      is_a_string = arr[1].strip
    else
      if line.strip.empty? and ! used_ids.include?(java_id)
        
        out_buff << "
  <object
    type=\"#{type}\"
    id=\"#{java_id}\">
<valueAttribute
      name=\"termId\"
      value=\"#{java_id}\" />
<valueAttribute
      name=\"termName\"
      value=\"#{miro_name}\" />
<valueAttribute
      name=\"displayLabel\"
      value=\"#{miro_name}\" />
 <valueAttribute
      name=\"oboNamespace\"
      value=\"#{namespace}\" />
 <valueAttribute
      name=\"definition\"
      value=\"#{miro_def}\" />
<valueAttribute
      name=\"inheritsTerm\"
      value=\"#{is_a_string}\" />
<valueAttribute
      name=\"inheritsTermName\"
      value=\"#{is_a_id}\" />
  </object> \n"
      used_ids << java_id
      end
  end
end


File.open(out_file,'w').write(out_buff)



