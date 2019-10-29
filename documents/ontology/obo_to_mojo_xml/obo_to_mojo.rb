#use me like this:
# ruby obo_to_mojo.rb ident_methods.obo mdss.entomology.IdentificationMethod

#obo file must be ASCII, not unicode, and have unix style newlines

require 'spreadsheet'



obo_file = "../MO - MDSS ontology.obo"
excel_file = "LOOKUP.XLS"
xml_file = "miro.xml"
out_buff = ""
obo = Hash.new
used_ids = []

sheet_names = Hash.new

sheet_names['Parasite tick list'] = nil
sheet_names['Targetsite resistance tick list'] = nil
sheet_names['Parasite detection methodology'] = 'dss.vector.solutions.mo.InfectivityMethodology'
sheet_names['Target site resistance methods'] = nil
sheet_names['diagnoistic test bio larvae'] = nil
sheet_names['Diagnostic Tests Bioassay adult'] = 'dss.vector.solutions.mo.InsecticideMethodology'
sheet_names['insecticide active ingreedients'] = 'dss.vector.solutions.mo.ActiveIngredient'
sheet_names['sPECIES names'] = 'dss.vector.solutions.mo.Specie'
sheet_names['Sheet 1'] = ''




def write_xml(id,display_label,classname,obo)
full_id = 'MIRO:'+id
str = obo[full_id]
xml = ""
miro_name = ''
value = ''
is_a_string = ''
is_a_id = ''
namespace = ''
miro_def = ''
miro_id = full_id.strip
java_id = full_id.strip.upcase.gsub(/[^A-Z0-9]/,'_')

unless str
  print "ERROR COULD NOT FIND MIRO:#{id} \n" 
  return ""
end

#print " ------- " +str

str.each_line do |line|
  array = line.strip.split(': ')
  case array[0]
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
    end
end        
        xml = "
  <object
    type=\"#{classname}\"
    id=\"#{java_id}\">
<valueAttribute
      name=\"termId\"
      value=\"#{java_id}\" />
<valueAttribute
      name=\"termName\"
      value=\"#{miro_name}\" />
<valueAttribute
      name=\"displayLabel\"
      value=\"#{display_label}\" />
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
      
     return xml
end

cur_id = ""
p "Reading OBO file: #{obo_file}"
File.open(obo_file , "r").each_line do |line|
  unless  line.strip.empty? or line =~/\[Term\]/i
  array = line.strip.split(': ')
  case array[0]
    when 'id'
      cur_id = line.gsub(/id:/,'').strip
    # p "cur id = #{cur_id}"
      obo[cur_id] = ""
    else
      obo[cur_id] = (obo[cur_id]+line) unless cur_id == ""
     end
   end
end

p "Read #{obo.length} terms"

book = Spreadsheet.open excel_file

book.worksheets.each do |sheet| 
	print sheet.name + "\n"
	classname = sheet_names[sheet.name]
	classname ||= sheet.row(0)[2]
	if classname
		sheet.each do |row|
		     if row[0] and row[1]
		       #print row[0] +','+ row[1].to_s + "\n" 
		       #used_ids << java_id
		       out_buff << write_xml(row[1].to_i.to_s,row[0], classname,obo)
		     end
	 	 end
	end
end

#print out_buff

File.open(xml_file,'w').write(out_buff)