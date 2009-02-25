/*
 * This file is part of OpenMPIS, the Open Source Missing Persons Information System.
 * Copyright (C) 2008  Rey Vincent Babilonia <rvbabilonia@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

function convert(password) {
    var stars = '';
    for (var i = 0; i < password.length; i++) {
        stars += '*';
    }
    return stars;
}

var countries = new Array('Afghanistan', 'Albania', 'Algeria', 'American Samoa', 'Andorra', 'Angola', 'Anguilla', 'Antarctica', 'Antigua And Barbuda', 'Argentina', 'Armenia', 'Aruba', 'Australia', 'Austria', 'Azerbaijan', 'Bahamas', 'Bahrain', 'Bangladesh', 'Barbados', 'Belarus', 'Belgium', 'Belize', 'Benin', 'Bermuda', 'Bhutan', 'Bolivia', 'Bosnia And Herzegowina', 'Botswana', 'Bouvet Island', 'Brazil', 'Brunei Darussalam', 'Bulgaria', 'Burkina Faso', 'Burundi', 'Cambodia', 'Cameroon', 'Canada', 'Cape Verde', 'Cayman Islands', 'Central African Rep.', 'Chad', 'Chile', 'China', 'Christmas Island', 'Cocos Islands', 'Colombia', 'Comoros', 'Congo', 'Cook Islands', 'Costa Rica', 'Croatia', 'Cuba', 'Cyprus', 'Czech Republic', 'Denmark', 'Djibouti', 'Dominica', 'Dominican Republic', 'East Timor', 'Ecuador', 'Egypt', 'El Salvador', 'Equatorial Guinea', 'Eritrea', 'Estonia', 'Ethiopia', 'Falkland Islands', 'Faroe Islands', 'Fiji', 'Finland', 'France', 'French Guiana', 'French Polynesia', 'Gabon', 'Gambia', 'Georgia', 'Germany', 'Ghana', 'Gibraltar', 'Greece', 'Greenland', 'Grenada', 'Guadeloupe', 'Guam', 'Guatemala', 'Guinea', 'Guinea-Bissau', 'Guyana', 'Haiti', 'Honduras', 'Hong Kong', 'Hungary', 'Iceland', 'India', 'Indonesia', 'Iran', 'Iraq', 'Ireland', 'Israel', 'Italy', 'Ivory Coast', 'Jamaica', 'Japan', 'Jordan', 'Kazakhstan', 'Kenya', 'Kiribati', 'Kuwait', 'Kyrgyzstan', 'Lao', 'Latvia', 'Lebanon', 'Lesotho', 'Liberia', 'Libya', 'Liechtenstein', 'Lithuania', 'Luxembourg', 'Macau', 'Macedonia', 'Madagascar', 'Malawi', 'Malaysia', 'Maldives', 'Mali', 'Malta', 'Marshall Islands', 'Martinique', 'Mauritania', 'Mauritius', 'Mayotte', 'Metropolitan France', 'Mexico', 'Micronesia', 'Moldova', 'Monaco', 'Mongolia', 'Montserrat', 'Morocco', 'Mozambique', 'Myanmar', 'Namibia', 'Nauru', 'Nepal', 'NetherLands Antilles', 'Netherlands', 'New Caledonia', 'New Zealand', 'Nicaragua', 'Niger', 'Nigeria', 'Niue', 'Norfolk Island', 'North Korea', 'Norway', 'Oman', 'Pakistan', 'Palau', 'Panama', 'Papua New Guinea', 'Paraguay', 'Peru', 'Philippines', 'Pitcairn', 'Poland', 'Portugal', 'Puerto Rico', 'Qatar', 'Reunion', 'Romania', 'Russian Federation', 'Rwanda', 'Saint Lucia', 'Samoa', 'San Marino', 'Saudi Arabia', 'Senegal', 'Seychelles', 'Sierra Leone', 'Singapore', 'Slovakia', 'Slovenia', 'Solomon Islands', 'Somalia', 'South Africa', 'South Korea', 'Spain', 'Sri Lanka', 'St. Helena', 'St. Pierre Miquelon', 'Sudan', 'Suriname', 'Swaziland', 'Sweden', 'Switzerland', 'Syria', 'Taiwan (China)', 'Tajikistan', 'Tanzania', 'Thailand', 'Togo', 'Tokelau', 'Tonga', 'Trinidad And Tobago', 'Tunisia', 'Turkey', 'Turkmenistan', 'Turks Caicos Islands', 'Tuvalu', 'Uganda', 'Ukraine', 'United Arab Emirates', 'United Kingdom', 'United States', 'Uruguay', 'Uzbekistan', 'Vanuatu', 'Vatican City State', 'Venezuela', 'Viet Nam', 'Virgin Islands (GB)', 'Virgin Islands (US)', 'Wallis Futuna Isl.', 'Western Sahara', 'Yemen', 'Yugoslavia', 'Zaire', 'Zambia', 'Zimbabwe');

var provinces = new Array('Abra', 'Agusan del Norte', 'Agusan del Sur', 'Aklan', 'Albay', 'Antique', 'Apayao', 'Aurora', 'Basilan', 'Bataan', 'Batanes', 'Batangas', 'Benguet', 'Biliran', 'Bohol', 'Bukidnon', 'Bulacan', 'Cagayan', 'Camarines Norte', 'Camarines Sur', 'Camiguin', 'Capiz', 'Catanduanes', 'Cavite', 'Cebu', 'Compostela Valley', 'Cotabato', 'Davao del Norte', 'Davao del Sur', 'Davao Oriental', 'Dinagat Islands', 'Eastern Samar', 'Guimaras', 'Ifugao', 'Ilocos Norte', 'Ilocos Sur', 'Iloilo', 'Isabela', 'Kalinga', 'La Union', 'Laguna', 'Lanao del Norte', 'Lanao del Sur', 'Leyte', 'Maguindanao', 'Marinduque', 'Masbate', 'Metro Manila', 'Misamis Occidental', 'Misamis Oriental', 'Mountain Province', 'Negros Occidental', 'Negros Oriental', 'Northern Samar', 'Nueva Ecija', 'Nueva Vizcaya', 'Occidental Mindoro', 'Oriental Mindoro', 'Palawan', 'Pampanga', 'Pangasinan', 'Quezon', 'Quirino', 'Rizal', 'Romblon', 'Samar', 'Sarangani', 'Shariff Kabunsuan', 'Siquijor', 'Sorsogon', 'South Cotabato', 'Southern Leyte', 'Sultan Kudarat', 'Sulu', 'Surigao del Norte', 'Surigao del Sur', 'Tarlac', 'Tawi-Tawi', 'Zambales', 'Zamboanga del Norte', 'Zamboanga del Sur', 'Zamboanga Sibugay');

var cities = new Array();
cities['Abra'] = new Array('Bangued', 'Boliney', 'Bucay', 'Bucloc', 'Daguioman', 'Danglas', 'Dolores', 'La Paz', 'Lacub', 'Lagangilang', 'Lagayan', 'Langiden', 'Licuan-Baay', 'Luba', 'Malibcong', 'Manabo', 'Peñarrubia', 'Pidigan', 'Pilar', 'Sallapadan', 'San Isidro', 'San Juan', 'San Quintin', 'Tayum', 'Tineg', 'Tubo', 'Villaviciosa');
cities['Agusan del Norte'] = new Array('Butuan City', 'Cabadbaran City', 'Buenavista', 'Carmen', 'Jabonga', 'Kitcharao', 'Las Nieves', 'Magallanes', 'Nasipit', 'Remedios T. Romualdez', 'Santiago', 'Tubay');
cities['Agusan del Sur'] = new Array('Bayugan', 'Bunawan', 'Esperanza', 'La Paz', 'Loreto', 'Prosperidad', 'Rosario', 'San Francisco', 'San Luis', 'Santa Josefa', 'Sibagat', 'Talacogon', 'Trento', 'Veruela');
cities['Aklan'] = new Array('Altavas', 'Balete', 'Banga', 'Batan', 'Buruanga', 'Ibajay', 'Kalibo', 'Lezo', 'Libacao', 'Madalag', 'Makato', 'Malay', 'Malinao', 'Nabas', 'New Washington', 'Numancia', 'Tangalan');
cities['Albay'] = new Array('Legazpi City', 'Ligao City', 'Tabaco City', 'Bacacay', 'Camalig', 'Daraga', 'Guinobatan', 'Jovellar', 'Libon', 'Malilipot', 'Malinao', 'Manito', 'Oas', 'Pio Duran', 'Polangui', 'Rapu-Rapu', 'Santo Domingo', 'Tiwi');
cities['Antique'] = new Array('Anini-y', 'Barbaza', 'Belison', 'Bugasong', 'Caluya', 'Culasi', 'Hamtic', 'Laua-an', 'Libertad', 'Pandan', 'Patnongon', 'San Jose', 'San Remigio', 'Sebaste', 'Sibalom', 'Tibiao', 'Tobias Fornier', 'Valderrama');
cities['Apayao'] = new Array('Calanasan', 'Conner', 'Flora', 'Kabugao', 'Luna', 'Pudtol', 'Santa Marcela');
cities['Aurora'] = new Array('Baler', 'Casiguran', 'Dilasag', 'Dinalungan', 'Dingalan', 'Dipaculao', 'Maria Aurora', 'San Luis');
cities['Basilan'] = new Array('Isabela City', 'Lamitan City', 'Akbar', 'Al-Barka', 'Hadji Mohammad Aju', 'Lantawan', 'Maluso', 'Sumisip', 'Tipo-Tipo', 'Tuburan', 'Ungkaya Pukan');
cities['Bataan'] = new Array('Balanga City', 'Abucay', 'Bagac', 'Dinalupihan', 'Hermosa', 'Limay', 'Mariveles', 'Morong', 'Orani', 'Orion', 'Pilar', 'Samal');
cities['Batanes'] = new Array('Basco', 'Itbayat', 'Ivana', 'Mahatao', 'Sabtang', 'Uyugan');
cities['Batangas'] = new Array('Batangas City', 'Lipa City', 'Tanauan City', 'Agoncillo', 'Alitagtag', 'Balayan', 'Balete', 'Bauan', 'Calaca', 'Calatagan', 'Cuenca', 'Ibaan', 'Laurel', 'Lemery', 'Lian', 'Lobo', 'Mabini', 'Malvar', 'Mataas na Kahoy', 'Nasugbu', 'Padre Garcia', 'Rosario', 'San Jose', 'San Juan', 'San Luis', 'San Nicolas', 'San Pascual', 'Santa Teresita', 'Santo Tomas', 'Taal', 'Talisay', 'Taysan', 'Tingloy', 'Tuy');
cities['Benguet'] = new Array('Baguio City', 'Atok', 'Bakun', 'Bokod', 'Buguias', 'Itogon', 'Kabayan', 'Kapangan', 'Kibungan', 'La Trinidad', 'Mankayan', 'Sablan', 'Tuba', 'Tublay');
cities['Biliran'] = new Array('Almeria', 'Biliran', 'Cabucgayan', 'Caibiran', 'Culaba', 'Kawayan', 'Maripipi', 'Naval');
cities['Bohol'] = new Array('Tagbilaran City', 'Alburquerque', 'Alicia', 'Anda', 'Antequera', 'Baclayon', 'Balilihan', 'Batuan', 'Bien Unido', 'Bilar', 'Buenavista', 'Calape', 'Candijay', 'Carmen', 'Catigbian', 'Clarin', 'Corella', 'Cortes', 'Dagohoy', 'Danao', 'Dauis', 'Dimiao', 'Duero', 'Garcia Hernandez', 'Guindulman', 'Inabanga', 'Jagna', 'Jetafe', 'Lila', 'Loay', 'Loboc', 'Loon', 'Mabini', 'Maribojoc', 'Panglao', 'Pilar', 'Pres. Carlos P. Garcia', 'Sagbayan', 'San Isidro', 'San Miguel', 'Sevilla', 'Sierra Bullones', 'Sikatuna', 'Talibon', 'Trinidad', 'Tubigon', 'Ubay', 'Valencia');
cities['Bukidnon'] = new Array('Malaybalay City', 'Valencia City', 'Baungon', 'Cabanglasan', 'Damulog', 'Dangcagan', 'Don Carlos', 'Impasug-Ong', 'Kadingilan', 'Kalilangan', 'Kibawe', 'Kitaotao', 'Lantapan', 'Libona', 'Malitbog', 'Manolo Fortich', 'Maramag', 'Pangantucan', 'Quezon', 'San Fernando', 'Sumilao', 'Talakag');
cities['Bulacan'] = new Array('Malolos City', 'Meycauayan City', 'San Jose del Monte City', 'Angat', 'Balagtas', 'Baliuag', 'Bocaue', 'Bulacan', 'Bustos', 'Calumpit', 'Doña Remedios Trinidad', 'Guiguinto', 'Hagonoy', 'Marilao', 'Norzagaray', 'Obando', 'Pandi', 'Paombong', 'Plaridel', 'Pulilan', 'San Ildefonso', 'San Miguel', 'San Rafael', 'Santa Maria');
cities['Cagayan'] = new Array('Tuguegarao City', 'Abulug', 'Alcala', 'Allacapan', 'Amulung', 'Aparri', 'Baggao', 'Ballesteros', 'Buguey', 'Calayan', 'Camalaniugan', 'Claveria', 'Enrile', 'Gattaran', 'Gonzaga', 'Iguig', 'Lal-Lo', 'Lasam', 'Pamplona', 'Peñablanca', 'Piat', 'Rizal', 'Sanchez-Mira', 'Santa Ana', 'Santa Praxedes', 'Santa Teresita', 'Santo Niño', 'Solana', 'Tuao');
cities['Camarines Norte'] = new Array('Basud', 'Capalonga', 'Daet', 'Jose Panganiban', 'Labo', 'Mercedes', 'Paracale', 'San Lorenzo Ruiz', 'San Vicente', 'Santa Elena', 'Talisay', 'Vinzons');
cities['Camarines Sur'] = new Array('Iriga City', 'Naga City', 'Baao', 'Balatan', 'Bato', 'Bombon', 'Buhi', 'Bula', 'Cabusao', 'Calabanga', 'Camaligan', 'Canaman', 'Caramoan', 'Del Gallego', 'Gainza', 'Garchitorena', 'Goa', 'Lagonoy', 'Libmanan', 'Lupi', 'Magarao', 'Milaor', 'Minalabac', 'Nabua', 'Ocampo', 'Pamplona', 'Pasacao', 'Pili', 'Presentacion', 'Ragay', 'Sagñay', 'San Fernando', 'San Jose', 'Sipocot', 'Siruma', 'Tigaon', 'Tinambac');
cities['Camiguin'] = new Array('Catarman', 'Guinsiliban', 'Mahinog', 'Mambajao', 'Sagay');
cities['Capiz'] = new Array('Roxas City', 'Cuartero', 'Dao', 'Dumalag', 'Dumarao', 'Ivisan', 'Jamindan', 'Ma-ayon', 'Mambusao', 'Panay', 'Panitan', 'Pilar', 'Pontevedra', 'President Roxas', 'Sapi-an', 'Sigma', 'Tapaz');
cities['Catanduanes'] = new Array('Bagamanoc', 'Baras', 'Bato', 'Caramoran', 'Gigmoto', 'Pandan', 'Panganiban', 'San Andres', 'San Miguel', 'Viga', 'Virac');
cities['Cavite'] = new Array('Cavite City', 'Tagaytay City', 'Trece Martires City', 'Alfonso', 'Amadeo', 'Bacoor', 'Carmona', 'Dasmariñas', 'Gen. Mariano Alvarez', 'Gen. Emilio Aguinaldo', 'Gen. Trias', 'Imus', 'Indang', 'Kawit', 'Magallanes', 'Maragondon', 'Mendez', 'Naic', 'Noveleta', 'Rosario', 'Silang', 'Tanza', 'Ternate');
cities['Cebu'] = new Array('Bogo City', 'Carcar City', 'Cebu City', 'Danao City', 'Lapu-Lapu City', 'Mandaue City', 'Talisay City', 'Toledo City', 'Naga City', 'Alcantara', 'Alcoy', 'Alegria', 'Aloguinsan', 'Argao', 'Asturias', 'Badian', 'Balamban', 'Bantayan', 'Barili', 'Boljoon', 'Borbon', 'Carmen', 'Catmon', 'Compostela', 'Consolacion', 'Cordoba', 'Daanbantayan', 'Dalaguete', 'Dumanjug', 'Ginatilan', 'Liloan', 'Madridejos', 'Malabuyoc', 'Medellin', 'Minglanilla', 'Moalboal', 'Oslob', 'Pilar', 'Pinamungahan', 'Poro', 'Ronda', 'Samboan', 'San Fernando', 'San Francisco', 'San Remigio', 'Santa Fe', 'Santander', 'Sibonga', 'Sogod', 'Tabogon', 'Tabuelan', 'Tuburan', 'Tudela');
cities['Compostela Valley'] = new Array('Compostela', 'Laak', 'Mabini', 'Maco', 'Maragusan', 'Mawab', 'Monkayo', 'Montevista', 'Nabunturan', 'New Bataan', 'Pantukan');
cities['Cotabato'] = new Array('Kidapawan City', 'Alamada', 'Aleosan', 'Antipas', 'Arakan', 'Banisilan', 'Carmen', 'Kabacan', 'Libungan', 'M\'Lang', 'Magpet', 'Makilala', 'Matalam', 'Midsayap', 'Pigkawayan', 'Pikit', 'President Roxas', 'Tulunan');
cities['Davao del Norte'] = new Array('Island Garden City of Samal', 'Panabo City', 'Tagum City', 'Asuncion', 'Braulio E. Dujali', 'Carmen', 'Kapalong', 'New Corella', 'San Isidro', 'Santo Tomas', 'Talaingod');
cities['Davao del Sur'] = new Array('Davao City', 'Digos City', 'Bansalan', 'Don Marcelino', 'Hagonoy', 'Jose Abad Santos', 'Kiblawan', 'Magsaysay', 'Malalag', 'Malita', 'Matanao', 'Padada', 'Santa Cruz', 'Santa Maria', 'Sarangani', 'Sulop');
cities['Davao Oriental'] = new Array('Mati City', 'Baganga', 'Banaybanay', 'Boston', 'Caraga', 'Cateel', 'Governor Generoso', 'Lupon', 'Manay', 'San Isidro', 'Tarragona');
cities['Dinagat Islands'] = new Array('Basilisia (Rizal)', 'Cagdianao', 'Dinagat', 'Libjo (Albor)', 'Loreto', 'San Jose', 'Tubajon');
cities['Eastern Samar'] = new Array('Borongan City', 'Arteche', 'Balangiga', 'Balangkayan', 'Can-avid', 'Dolores', 'General MacArthur', 'Giporlos', 'Guiuan', 'Hernani', 'Jipapad', 'Lawaan', 'Llorente', 'Maslog', 'Maydolong', 'Mercedes', 'Oras', 'Quinapondan', 'Salcedo', 'San Julian', 'San Policarpo', 'Sulat', 'Taft');
cities['Guimaras'] = new Array('Buenavista', 'Jordan', 'Nueva Valencia', 'San Lorenzo', 'Sibunag');
cities['Ifugao'] = new Array('Aguinaldo', 'Alfonso Lista', 'Asipulo', 'Banaue', 'Hingyon', 'Hungduan', 'Kiangan', 'Lagawe', 'Lamut', 'Mayoyao', 'Tinoc');
cities['Ilocos Norte'] = new Array('Batac City', 'Laoag City', 'Adams', 'Bacarra', 'Badoc', 'Bangui', 'Banna', 'Burgos', 'Carasi', 'Currimao', 'Dingras', 'Dumalneg', 'Marcos', 'Nueva Era', 'Pagudpud', 'Paoay', 'Pasuquin', 'Piddig', 'Pinili', 'San Nicolas', 'Sarrat', 'Solsona', 'Vintar');
cities['Ilocos Sur'] = new Array('Candon City', 'Vigan City', 'Alilem', 'Banayoyo', 'Bantay', 'Burgos', 'Cabugao', 'Caoayan', 'Cervantes', 'Galimuyod', 'Gregorio Del Pilar', 'Lidlidda', 'Magsingal', 'Nagbukel', 'Narvacan', 'Quirino', 'Salcedo', 'San Emilio', 'San Esteban', 'San Ildefonso', 'San Juan', 'San Vicente', 'Santa', 'Santa Catalina', 'Santa Cruz', 'Santa Lucia', 'Santa Maria', 'Santiago', 'Santo Domingo', 'Sigay', 'Sinait', 'Sugpon', 'Suyo', 'Tagudin');
cities['Iloilo'] = new Array('Passi City', 'Iloilo City', 'Ajuy', 'Alimodian', 'Anilao', 'Badiangan', 'Balasan', 'Banate', 'Barotac Nuevo', 'Barotac Viejo', 'Batad', 'Bingawan', 'Cabatuan', 'Calinog', 'Carles', 'Concepcion', 'Dingle', 'Dueñas', 'Dumangas', 'Estancia', 'Guimbal', 'Igbaras', 'Janiuay', 'Lambunao', 'Leganes', 'Lemery', 'Leon', 'Maasin', 'Miagao', 'Mina', 'New Lucena', 'Oton', 'Pavia', 'Pototan', 'San Dionisio', 'San Enrique', 'San Joaquin', 'San Miguel', 'San Rafael', 'Santa Barbara', 'Sara', 'Tigbauan', 'Tubungan', 'Zarraga');
cities['Isabela'] = new Array('Cauayan City', 'Santiago City', 'Alicia', 'Angadanan', 'Aurora', 'Benito Soliven', 'Burgos', 'Cabagan', 'Cabatuan', 'Cordon', 'Delfin Albano', 'Dinapigue', 'Divilacan', 'Echague', 'Gamu', 'Ilagan', 'Jones', 'Luna', 'Maconacon', 'Mallig', 'Naguilian', 'Palanan', 'Quezon', 'Quirino', 'Ramon', 'Reina Mercedes', 'Roxas', 'San Agustin', 'San Guillermo', 'San Isidro', 'San Manuel', 'San Mariano', 'San Mateo', 'San Pablo', 'Santa Maria', 'Santo Tomas', 'Tumauini');
cities['Kalinga'] = new Array('Tabuk City', 'Balbalan', 'Lubuagan', 'Pasil', 'Pinukpuk', 'Rizal', 'Tanudan', 'Tinglayan');
cities['La Union'] = new Array('San Fernando City', 'Agoo', 'Aringay', 'Bacnotan', 'Bagulin', 'Balaoan', 'Bangar', 'Bauang', 'Burgos', 'Caba', 'Luna', 'Naguilian', 'Pugo', 'Rosario', 'San Gabriel', 'San Juan', 'Santo Tomas', 'Santol', 'Sudipen', 'Tubao');
cities['Laguna'] = new Array('Calamba City', 'San Pablo City', 'Santa Rosa City', 'Alaminos', 'Bay', 'Biñan', 'Cabuyao', 'Calauan', 'Cavinti', 'Famy', 'Kalayaan', 'Liliw', 'Los Baños', 'Luisiana', 'Lumban', 'Mabitac', 'Magdalena', 'Majayjay', 'Nagcarlan', 'Paete', 'Pagsanjan', 'Pakil', 'Pangil', 'Pila', 'Rizal', 'San Pedro', 'Santa Cruz', 'Santa Maria', 'Siniloan', 'Victoria');
cities['Lanao del Norte'] = new Array('Iligan City', 'Bacolod', 'Baloi', 'Baroy', 'Kapatagan', 'Kauswagan', 'Kolambugan', 'Lala', 'Linamon', 'Magsaysay', 'Maigo', 'Matungao', 'Munai', 'Nunungan', 'Pantao Ragat', 'Pantar', 'Poona Piagapo', 'Salvador', 'Sapad', 'Sultan Naga Dimaporo', 'Tagoloan', 'Tangcal', 'Tubod');
cities['Lanao del Sur'] = new Array('Marawi City', 'Bacolod-Kalawi', 'Balabagan', 'Balindong', 'Bayang', 'Binidayan', 'Buadiposo-Buntong', 'Bubong', 'Bumbaran', 'Butig', 'Calanogas', 'Ditsaan-Ramain', 'Ganassi', 'Kapai', 'Kapatagan', 'Lumba-Bayabao', 'Lumbaca-Unayan', 'Lumbatan', 'Lumbayanague', 'Madalum', 'Madamba', 'Maguing', 'Malabang', 'Marantao', 'Marogong', 'Masiu', 'Mulondo', 'Pagayawan', 'Piagapo', 'Poona Bayabao', 'Pualas', 'Saguiaran', 'Sultan Dumalondong', 'Picong', 'Tagoloan Ii', 'Tamparan', 'Taraka', 'Tubaran', 'Tugaya', 'Wao');
cities['Leyte'] = new Array('Baybay City', 'Ormoc City', 'Tacloban City', 'Abuyog', 'Alangalang', 'Albuera', 'Babatngon', 'Barugo', 'Bato', 'Burauen', 'Calubian', 'Capoocan', 'Carigara', 'Dagami', 'Dulag', 'Hilongos', 'Hindang', 'Inopacan', 'Isabel', 'Jaro', 'Javier', 'Julita', 'Kananga', 'La Paz', 'Leyte', 'Macarthur', 'Mahaplag', 'Matag-ob', 'Matalom', 'Mayorga', 'Merida', 'Palo', 'Palompon', 'Pastrana', 'San Isidro', 'San Miguel', 'Santa Fe', 'Tabango', 'Tabontabon', 'Tanauan', 'Tolosa', 'Tunga', 'Villaba');
cities['Maguindanao'] = new Array('Cotabato City', 'Ampatuan', 'Buluan', 'Datu Abdullah Sangki', 'Datu Anggal Midtimbang', 'Datu Paglas', 'Datu Piang', 'Datu Saudi-Ampatuan', 'Datu Unsay', 'Gen. S. K. Pendatun', 'Guindulungan', 'Mamasapano', 'Mangudadatu', 'Pagagawan', 'Pagalungan', 'Paglat', 'Pandag', 'Rajah Buayan', 'Shariff Aguak', 'South Upi', 'Sultan sa Barongis', 'Talayan', 'Talitay');
cities['Marinduque'] = new Array('Boac', 'Buenavista', 'Gasan', 'Mogpog', 'Santa Cruz', 'Torrijos');
cities['Masbate'] = new Array('Masbate City', 'Aroroy', 'Baleno', 'Balud', 'Batuan', 'Cataingan', 'Cawayan', 'Claveria', 'Dimasalang', 'Esperanza', 'Mandaon', 'Milagros', 'Mobo', 'Monreal', 'Palanas', 'Pio V. Corpuz', 'Placer', 'San Fernando', 'San Jacinto', 'San Pascual', 'Uson');
cities['Metro Manila'] = new Array('Caloocan', 'Las Piñas', 'Makati', 'Malabon', 'Mandaluyong', 'Manila', 'Marikina', 'Muntinlupa', 'Navotas', 'Parañaque', 'Pasay', 'Pasig', 'Quezon City', 'San Juan', 'Taguig', 'Valenzuela', 'Pateros');
cities['Misamis Occidental'] = new Array('Oroquieta City', 'Ozamis City', 'Tangub City', 'Aloran', 'Baliangao', 'Bonifacio', 'Calamba', 'Clarin', 'Concepcion', 'Don Victoriano Chiongbian', 'Jimenez', 'Lopez Jaena', 'Panaon', 'Plaridel', 'Sapang Dalaga', 'Sinacaban', 'Tudela');
cities['Misamis Oriental'] = new Array('Cagayan de Oro', 'Gingoog City', 'El Salvador City', 'Alubijid', 'Balingasag', 'Balingoan', 'Binuangan', 'Claveria', 'El Salvador', 'Gitagum', 'Initao', 'Jasaan', 'Kinoguitan', 'Lagonglong', 'Laguindingan', 'Libertad', 'Lugait', 'Magsaysay', 'Manticao', 'Medina', 'Naawan', 'Opol', 'Salay', 'Sugbongcogon', 'Tagoloan', 'Talisayan', 'Villanueva');
cities['Mountain Province'] = new Array('Barlig', 'Bauko', 'Besao', 'Bontoc', 'Natonin', 'Paracelis', 'Sabangan', 'Sadanga', 'Sagada', 'Tadian');
cities['Negros Occidental'] = new Array('Bacolod City', 'Bago City', 'Cadiz City', 'Escalante City', 'Himamaylan City', 'Kabankalan City', 'La Carlota City', 'Sagay City', 'San Carlos City', 'Silay City', 'Sipalay City', 'Talisay City', 'Victorias City', 'Binalbagan', 'Calatrava', 'Candoni', 'Cauayan', 'Enrique B. Magalona', 'Hinigaran', 'Hinoba-an', 'Ilog', 'Isabela', 'La Castellana', 'Manapla', 'Moises Padilla', 'Murcia', 'Pontevedra', 'Pulupandan', 'Salvador Benedicto', 'San Enrique', 'Toboso', 'Valladolid');
cities['Negros Oriental'] = new Array('Bais', 'Bayawan', 'Canlaon', 'Dumaguete', 'Guihulngan', 'Tanjay', 'Amlan', 'Ayungon', 'Bacong', 'Basay', 'Bindoy', 'Dauin', 'Jimalalud', 'La Libertad', 'Mabinay', 'Manjuyod', 'Pamplona', 'San Jose', 'Santa Catalina', 'Siaton', 'Sibulan', 'Tayasan', 'Valencia', 'Vallehermoso', 'Zamboanguita');
cities['Northern Samar'] = new Array('Allen', 'Biri', 'Bobon', 'Capul', 'Catarman', 'Catubig', 'Gamay', 'Laoang', 'Lapinig', 'Las Navas', 'Lavezares', 'Lope de Vega', 'Mapanas', 'Mondragon', 'Palapag', 'Pambujan', 'Rosario', 'San Antonio', 'San Isidro', 'San Jose', 'San Roque', 'San Vicente', 'Silvino Lobos', 'Victoria');
cities['Nueva Ecija'] = new Array('Cabanatuan City', 'Gapan City', 'Palayan City', 'San Jose City', 'Science City of Muñoz', 'Aliaga', 'Bongabon', 'Cabiao', 'Carranglan', 'Cuyapo', 'Gabaldon', 'General Mamerto Natividad', 'General Tinio', 'Guimba', 'Jaen', 'Laur', 'Licab', 'Llanera', 'Lupao', 'Nampicuan', 'Pantabangan', 'Peñaranda', 'Quezon', 'Rizal', 'San Antonio', 'San Isidro', 'San Leonardo', 'Santa Rosa', 'Santo Domingo', 'Talavera', 'Talugtug', 'Zaragoza');
cities['Nueva Vizcaya'] = new Array('Alfonso Castaneda', 'Ambaguio', 'Aritao', 'Bagabag', 'Bambang', 'Bayombong', 'Diadi', 'Dupax del Norte', 'Dupax del Sur', 'Kasibu', 'Kayapa', 'Quezon', 'Santa Fe', 'Solano', 'Villaverde');
cities['Occidental Mindoro'] = new Array('Abra de Ilog', 'Calintaan', 'Looc', 'Lubang', 'Magsaysay', 'Mamburao', 'Paluan', 'Rizal', 'Sablayan', 'San Jose', 'Santa Cruz');
cities['Oriental Mindoro'] = new Array('Calapan City', 'Baco', 'Bansud', 'Bongabong', 'Bulalacao', 'Gloria', 'Mansalay', 'Naujan', 'Pinamalayan', 'Pola', 'Puerto Galera', 'Roxas', 'San Teodoro', 'Socorro', 'Victoria');
cities['Palawan'] = new Array('Puerto Princesa City', 'Aborlan', 'Agutaya', 'Araceli', 'Balabac', 'Bataraza', 'Brooke\'s Point', 'Busuanga', 'Cagayancillo', 'Coron', 'Culion', 'Cuyo', 'Dumaran', 'El Nido', 'Kalayaan', 'Linapacan', 'Magsaysay', 'Narra', 'Quezon', 'Rizal', 'Roxas', 'San Vicente', 'Sofronio Española', 'Taytay');
cities['Pampanga'] = new Array('Angeles City', 'San Fernando City', 'Apalit', 'Arayat', 'Bacolor', 'Candaba', 'Floridablanca', 'Guagua', 'Lubao', 'Mabalacat', 'Macabebe', 'Magalang', 'Masantol', 'Mexico', 'Minalin', 'Porac', 'San Luis', 'San Simon', 'Santa Ana', 'Santa Rita', 'Santo Tomas', 'Sasmuan');
cities['Pangasinan'] = new Array('Alaminos City', 'Dagupan City', 'San Carlos City', 'Urdaneta City', 'Agno', 'Aguilar', 'Alcala', 'Anda', 'Asingan', 'Balungao', 'Bani', 'Basista', 'Bautista', 'Bayambang', 'Binalonan', 'Binmaley', 'Bolinao', 'Bugallon', 'Burgos', 'Calasiao', 'Dasol', 'Infanta', 'Labrador', 'Laoac', 'Lingayen', 'Mabini', 'Malasiqui', 'Manaoag', 'Mangaldan', 'Mangatarem', 'Mapandan', 'Natividad', 'Pozzorubio', 'Rosales', 'San Fabian', 'San Jacinto', 'San Manuel', 'San Nicolas', 'San Quintin', 'Santa Barbara', 'Santa Maria', 'Santo Tomas', 'Sison', 'Sual', 'Tayug', 'Umingan', 'Urbiztondo', 'Villasis');
cities['Quezon'] = new Array('Lucena City', 'Tayabas City', 'Agdangan', 'Alabat', 'Atimonan', 'Buenavista', 'Burdeos', 'Calauag', 'Candelaria', 'Catanauan', 'Dolores', 'General Luna', 'General Nakar', 'Guinayangan', 'Gumaca', 'Infanta', 'Jomalig', 'Lopez', 'Lucban', 'Macalelon', 'Mauban', 'Mulanay', 'Padre Burgos', 'Pagbilao', 'Panukulan', 'Patnanungan', 'Perez', 'Pitogo', 'Plaridel', 'Polillo', 'Quezon', 'Real', 'Sampaloc', 'San Andres', 'San Antonio', 'San Francisco', 'San Narciso', 'Sariaya', 'Tagkawayan', 'Tiaong', 'Unisan');
cities['Quirino'] = new Array('Aglipay', 'Cabarroguis', 'Diffun', 'Maddela', 'Nagtipunan', 'Saguday');
cities['Rizal'] = new Array('Antipolo City', 'Angono', 'Baras', 'Binangonan', 'Cainta', 'Cardona', 'Jalajala', 'Morong', 'Pililla', 'Rodriguez', 'San Mateo', 'Tanay', 'Taytay', 'Teresa');
cities['Romblon'] = new Array('Alcantara', 'Banton', 'Cajidiocan', 'Calatrava', 'Concepcion', 'Corcuera', 'Ferrol', 'Looc', 'Magdiwang', 'Odiongan', 'Romblon', 'San Agustin', 'San Andres', 'San Fernando', 'San Jose', 'Santa Fe', 'Santa Maria');
cities['Samar'] = new Array('Catbalogan City', 'Calbayog City', 'Almagro', 'Basey', 'Calbiga', 'Daram', 'Gandara', 'Hinabangan', 'Jiabong', 'Marabut', 'Matuguinao', 'Motiong', 'Pagsanghan', 'Paranas', 'Pinabacdao', 'San Jorge', 'San Jose De Buan', 'San Sebastian', 'Santa Margarita', 'Santa Rita', 'Santo Niño', 'Tagapul-an', 'Talalora', 'Tarangnan', 'Villareal', 'Zumarraga');
cities['Sarangani'] = new Array('Alabel', 'Glan', 'Kiamba', 'Maasim', 'Maitum', 'Malapatan', 'Malungon');
cities['Shariff Kabunsuan'] = new Array('Barira', 'Buldon', 'Datu Blah T. Sinsuat', 'Datu Odin Sinsuat', 'Kabuntalan', 'Matanog', 'Northern Kabuntalan', 'Parang', 'Sultan Kudarat', 'Sultan Mastura', 'Upi');
cities['Siquijor'] = new Array('Enrique Villanueva', 'Larena', 'Lazi', 'Maria', 'San Juan', 'Siquijor');
cities['Sorsogon'] = new Array('Sorsogon City', 'Barcelona', 'Bulan', 'Bulusan', 'Casiguran', 'Castilla', 'Donsol', 'Gubat', 'Irosin', 'Juban', 'Magallanes', 'Matnog', 'Pilar', 'Prieto Diaz', 'Santa Magdalena');
cities['South Cotabato'] = new Array('General Santos City', 'Koronadal City', 'Banga', 'Lake Sebu', 'Norala', 'Polomolok', 'Santo Niño', 'Surallah', 'T\'Boli', 'Tampakan', 'Tantangan', 'Tupi');
cities['Southern Leyte'] = new Array('Maasin City', 'Anahawan', 'Bontoc', 'Hinunangan', 'Hinundayan', 'Libagon', 'Liloan', 'Limasawa', 'Macrohon', 'Malitbog', 'Padre Burgos', 'Pintuyan', 'Saint Bernard', 'San Francisco', 'San Juan', 'San Ricardo', 'Silago', 'Sogod', 'Tomas Oppus');
cities['Sultan Kudarat'] = new Array('Tacurong City', 'Bagumbayan', 'Columbio', 'Esperanza', 'Isulan', 'Kalamansig', 'Lambayong', 'Lebak', 'Lutayan', 'Palimbang', 'President Quirino', 'Sen. Ninoy Aquino');
cities['Sulu'] = new Array('Hadji Panglima Tahil', 'Indanan', 'Jolo', 'Kalingalan Caluang', 'Lugus', 'Luuk', 'Maimbung', 'Old Panamao', 'Omar', 'Pandami', 'Panglima Estino', 'Pangutaran', 'Parang', 'Pata', 'Patikul', 'Siasi', 'Talipao', 'Tapul', 'Tongkil');
cities['Surigao del Norte'] = new Array('Surigao City', 'Alegria', 'Bacuag', 'Burgos', 'Claver', 'Dapa', 'Del Carmen', 'General Luna', 'Gigaquit', 'Mainit', 'Malimono', 'Pilar', 'Placer', 'San Benito', 'San Francisco', 'San Isidro', 'Santa Monica', 'Sison', 'Socorro', 'Tagana-an', 'Tubod');
cities['Surigao del Sur'] = new Array('Bislig City', 'Tandag City', 'Barobo', 'Bayabas', 'Cagwait', 'Cantilan', 'Carmen', 'Carrascal', 'Cortes', 'Hinatuan', 'Lanuza', 'Lianga', 'Lingig', 'Madrid', 'Marihatag', 'San Agustin', 'San Miguel', 'Tagbina', 'Tago');
cities['Tarlac'] = new Array('Tarlac City', 'Anao', 'Bamban', 'Camiling', 'Capas', 'Concepcion', 'Gerona', 'La Paz', 'Mayantoc', 'Moncada', 'Paniqui', 'Pura', 'Ramos', 'San Clemente', 'San Jose', 'San Manuel', 'Santa Ignacia', 'Victoria');
cities['Tawi-Tawi'] = new Array('Bongao', 'Languyan', 'Mapun', 'Panglima Sugala', 'Sapa-Sapa', 'Sibutu', 'Simunul', 'Sitangkai', 'South Ubian', 'Tandubas', 'Turtle Islands');
cities['Zambales'] = new Array('Olongapo City', 'Botolan', 'Cabangan', 'Candelaria', 'Castillejos', 'Iba', 'Masinloc', 'Palauig', 'San Antonio', 'San Felipe', 'San Marcelino', 'San Narciso', 'Santa Cruz', 'Subic');
cities['Zamboanga del Norte'] = new Array('Dapitan City', 'Dipolog City', 'Bacungan', 'Baliguian', 'Godod', 'Gutalac', 'Jose Dalman', 'Kalawit', 'Katipunan', 'La Libertad', 'Labason', 'Liloy', 'Manukan', 'Mutia', 'Piñan', 'Polanco', 'Pres. Manuel A. Roxas', 'Rizal', 'Salug', 'Sergio Osmeña Sr.', 'Siayan', 'Sibuco', 'Sibutad', 'Sindangan', 'Siocon', 'Sirawai', 'Tampilisan');
cities['Zamboanga del Sur'] = new Array('Pagadian City', 'Zamboanga City', 'Aurora', 'Bayog', 'Dimataling', 'Dinas', 'Dumalinao', 'Dumingag', 'Guipos', 'Josefina', 'Kumalarang', 'Labangan', 'Lakewood', 'Lapuyan', 'Mahayag', 'Margosatubig', 'Midsalip', 'Molave', 'Pitogo', 'Ramon Magsaysay', 'San Miguel', 'San Pablo', 'Sominot', 'Tabina', 'Tambulig', 'Tigbao', 'Tukuran', 'Vincenzo A. Sagun');
cities['Zamboanga Sibugay'] = new Array('Alicia', 'Buug', 'Diplahan', 'Imelda', 'Ipil', 'Kabasalan', 'Mabuhay', 'Malangas', 'Naga', 'Olutanga', 'Payao', 'Roseller Lim', 'Siay', 'Talusan', 'Titay', 'Tungawan');

function setCities(provinceSelect, cityField) {
	cityField.options.length = 0;
	var province = provinceSelect.options[provinceSelect.selectedIndex].text;
    for (var i = 0; i < cities[province].length; i++) {
        cityField.options[cityField.length] = new Option(cities[province][i], cities[province][i]);
    }
}

function setDefaultCities(province, city) {
    province = (province != '') ? province : 'Abra';
    cityField = document.getElementById('cityfield');
    if (document.getElementById('missingfromcityfield') != null)
        missingFromCityField = document.getElementById('missingfromcityfield');
    if (document.getElementById('possiblecityfield') != null)
        possibleCityField = document.getElementById('possiblecityfield');
    if (document.getElementById('institutioncityfield') != null)
        institutionCityField = document.getElementById('institutioncityfield');
	cityField.options.length = 0;
    for (var i = 0; i < cities[province].length; i++) {
        if ((city != '') && (cities[province][i] == city)) {
            if (document.getElementById('missingfromcityfield') != null)
                missingFromCityField.options[cityField.length] = new Option(cities[province][i], cities[province][i], true);
            if (document.getElementById('possiblecityfield') != null)
                possibleCityField.options[cityField.length] = new Option(cities[province][i], cities[province][i], true);
            if (document.getElementById('institutioncityfield') != null)
                institutionCityField.options[cityField.length] = new Option(cities[province][i], cities[province][i], true);
            cityField.options[cityField.length] = new Option(cities[province][i], cities[province][i], true);
        } else {
            if (document.getElementById('missingfromcityfield') != null)
                missingFromCityField.options[cityField.length] = new Option(cities[province][i], cities[province][i]);
            if (document.getElementById('possiblecityfield') != null)
                possibleCityField.options[cityField.length] = new Option(cities[province][i], cities[province][i]);
            if (document.getElementById('institutioncityfield') != null)
                institutionCityField.options[cityField.length] = new Option(cities[province][i], cities[province][i]);
            cityField.options[cityField.length] = new Option(cities[province][i], cities[province][i]);
        }
    }
}

function setProvinces(province) {
    provinceField = document.getElementById('provincefield');
    if (document.getElementById('missingfromprovincefield') != null)
        missingFromProvinceField = document.getElementById('missingfromprovincefield');
    if (document.getElementById('possibleprovincefield') != null)
        possibleProvinceField = document.getElementById('possibleprovincefield');
    if (document.getElementById('institutionprovincefield') != null)
        institutionProvinceField = document.getElementById('institutionprovincefield');
	provinceField.options.length = 0;
	for (var i = 0; i < provinces.length; i++) {
        if (provinces[i] == province) {
            if (document.getElementById('missingfromprovincefield') != null)
                missingFromProvinceField.options[provinceField.length] = new Option(provinces[i], provinces[i], true);
            if (document.getElementById('possibleprovincefield') != null)
                possibleProvinceField.options[provinceField.length] = new Option(provinces[i], provinces[i], true);
            if (document.getElementById('institutionprovincefield') != null)
                institutionProvinceField.options[provinceField.length] = new Option(provinces[i], provinces[i], true);
            provinceField.options[provinceField.length] = new Option(provinces[i], provinces[i], true);
        } else {
            if (document.getElementById('missingfromprovincefield') != null)
                missingFromProvinceField.options[provinceField.length] = new Option(provinces[i], provinces[i]);
            if (document.getElementById('possibleprovincefield') != null)
                possibleProvinceField.options[provinceField.length] = new Option(provinces[i], provinces[i]);
            if (document.getElementById('institutionprovincefield') != null)
                institutionProvinceField.options[provinceField.length] = new Option(provinces[i], provinces[i]);
            provinceField.options[provinceField.length] = new Option(provinces[i], provinces[i]);
        }
    }
}

function setCountries(country) {
    country = (country != '') ? country : 'Philippines';
    countryField = document.getElementById('countryfield');
    if (document.getElementById('missingfromcountryfield') != null)
        missingFromCountryField = document.getElementById('missingfromcountryfield');
    if (document.getElementById('possiblecountryfield') != null)
        possibleCountryField = document.getElementById('possiblecountryfield');
    if (document.getElementById('institutioncountryfield') != null)
        institutionCountryField = document.getElementById('institutioncountryfield');
	countryField.options.length = 0;
    for (var i = 0; i < countries.length; i++) {
        if ((country != '') && (countries[i] == country)) {
            countryField.options[countryField.length] = new Option(countries[i], countries[i], true);
            if (document.getElementById('missingfromcountryfield') != null)
                missingFromCountryField.options[countryField.length] = new Option(countries[i], countries[i], true);
            if (document.getElementById('possiblecountryfield') != null)
                possibleCountryField.options[countryField.length] = new Option(countries[i], countries[i], true);
            if (document.getElementById('institutioncountryfield') != null)
                institutionCountryField.options[countryField.length] = new Option(countries[i], countries[i], true);
        } else {
            countryField.options[countryField.length] = new Option(countries[i], countries[i]);
            if (document.getElementById('missingfromcountryfield') != null)
                missingFromCountryField.options[countryField.length] = new Option(countries[i], countries[i]);
            if (document.getElementById('possiblecountryfield') != null)
                possibleCountryField.options[countryField.length] = new Option(countries[i], countries[i]);
            if (document.getElementById('institutioncountryfield') != null)
                institutionCountryField.options[countryField.length] = new Option(countries[i], countries[i]);
        }
    }
}

function toggleCountry(countrySelect, provinceField, provinceTextField, cityField, cityTextField) {
    var country = countrySelect.options[countrySelect.selectedIndex].text;
    if (country != 'Philippines') {
        provinceField.disabled = true;
        provinceField.style.display = 'none';
        provinceTextField.disabled = false;
        provinceTextField.style.display = 'block';
        cityField.disabled = true;
        cityField.style.display = 'none';
        cityTextField.disabled = false;
        cityTextField.style.display = 'block';
    } else {
        provinceField.disabled = false;
        provinceField.style.display = 'block';
        provinceTextField.disabled = true;
        provinceTextField.style.display = 'none';
        cityField.disabled = false;
        cityField.style.display = 'block';
        cityTextField.disabled = true;
        cityTextField.style.display = 'none';
    }
}

function toggleMissingOrFound(type) {
    if (type <= 4) {
        document.getElementById('ifmissing').style.display = 'block';
        document.getElementById('iffound').style.display = 'none';
    } else {
        document.getElementById('ifmissing').style.display = 'none';
        document.getElementById('iffound').style.display = 'block';
    }
}

function setAge() {
    var today = new Date();
    var currentMonth = today.getMonth();
    var currentDay = today.getDate();
    var currentYear = today.getFullYear();
    var month = document.getElementById('monthfield').value - 1;
    var day = document.getElementById('dayfield').value;
    var year = document.getElementById('yearfield').value;
    ageField = document.getElementById('agefield');
    if ((currentMonth > month) || ((currentMonth == month) && (currentDay >= day))) {
        ageField.value = currentYear - year;
    } else if ((currentMonth < month) || ((currentMonth == month) && (currentDay < day))) {
        ageField.value = currentYear - year - 1;
    }
}

function setCm() {
    var feet = document.getElementById('feetfield').value;
    var inches = document.getElementById('inchesfield').value;
    cmField = document.getElementById('cmfield');
    cmField.value = ((feet * 12) + (inches * 1)) * 2.54;
}

function setTimeElapsed(year, month, day, daysField) {
    var today = new Date();
    var dateMissing = new Date(year, month - 1, day);
    var oneDay = 1000 * 60 * 60 * 24; // milliseconds x seconds x minutes x hours
    var difference = today.getTime() - dateMissing.getTime();
    daysField.value = Math.floor(difference / oneDay);
}