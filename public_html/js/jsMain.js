function f_ColocarImagenWord(indicadorImagen) {

    if (indicadorImagen ==1)
       return '/imagenes/btn_word.png';
    else if(indicadorImagen ==2) 
       return '/imagenes/btn_word_m.png';
    else
       return '';
    
}

function f_CountLeft(field, count, max) {
    if (document.getElementById(field).value.length > max)
        document.getElementById(field).value = document.getElementById(field).value.substring(0, max);
    else 
        document.getElementById(count).value = max - document.getElementById(field).value.length;
}

function f_valida_datos() {
    var vcAnioResolucion = document.getElementById('frmNuevaResolucion:txtAnioResolucion').value;
    mNuError = 0;
    var vcMensaje = "Por favor verifique lo siguiente:\n";
    //Nro Resolución
    if (document.getElementById('frmNuevaResolucion:txtNroResolucion').value.length <= 0) {
        mNuError++;
        f_colorea('frmNuevaResolucion', 'NroResolucion', 'red');
        vcMensaje += "-Debe ingresar el número de resolución.\n"
    } else {
        f_colorea('frmNuevaResolucion', 'NroResolucion', 'black');
    }
    
    //Anio Resolución
    if (vcAnioResolucion<1990) {
        mNuError++;
        f_colorea('frmNuevaResolucion', 'NroResolucion', 'red');
        vcMensaje += "-Debe ingresar un año de resolución válido.\n"
    } else {
        f_colorea('frmNuevaResolucion', 'NroResolucion', 'black');
    }
    
    //Sigla Resolución
    
    //Fecha Resolución
    if (document.getElementById('frmNuevaResolucion:clFechaResolucionInputDate').value.length <= 0) {
        mNuError++;
        f_colorea('frmNuevaResolucion', 'FechaResolucion', 'red');
        vcMensaje += "-Debe ingresar una fecha válida.\n"
    } else {
        f_colorea('frmNuevaResolucion', 'FechaResolucion', 'black');
        //El año de resolución debe coincidir con la fecha de resolución
        var vFechaResolucion = document.getElementById('frmNuevaResolucion:clFechaResolucionInputDate').value;
        if(vFechaResolucion.substring(6,vFechaResolucion.length) == vcAnioResolucion){
            f_colorea('frmNuevaResolucion', 'FechaResolucion', 'black');
        }else{
            vcMensaje += "-El año de la resolución debe coincidir con el año de la fecha de resolución.\n"
            mNuError++;
            f_colorea('frmNuevaResolucion', 'FechaResolucion', 'red');
        }
    }
    
    //Sumilla
    if (document.getElementById('frmNuevaResolucion:txtSumilla').value.length <= 0) {
        mNuError++;
        vcMensaje += "-Debe ingresar una sumilla.\n"
        f_colorea('frmNuevaResolucion', 'Sumilla', 'red');
    } else {
        f_colorea('frmNuevaResolucion', 'Sumilla', 'black');
    }
    
    //Partes
    if (document.getElementById('frmNuevaResolucion:txtPartes').value.length <= 0) {
        mNuError++;
        vcMensaje += "-Debe ingresar las partes de la resolución.\n"
        f_colorea('frmNuevaResolucion', 'Partes', 'red');
    } else {
        f_colorea('frmNuevaResolucion', 'Partes', 'black');
    }
    
    //Nro Expediente
    var table = document.getElementById("frmNuevaResolucion:tblExpedientes");
    var tamanio=0;
    for (var i = 0, row; row = table.rows[i]; i++) {
       tamanio = i;
    }

    if (tamanio<2) {
        mNuError++;
        vcMensaje += "-Debe ingresar al menos un expediente.\n"
        f_colorea('frmNuevaResolucion', 'Expedientes', 'red');
    } else {
        f_colorea('frmNuevaResolucion', 'Expedientes', 'black');
    }
    
    //Resolución
    table = document.getElementById("frmNuevaResolucion:tblDocumentos");
    tamanio=0;
    for (var i = 0, row; row = table.rows[i]; i++) {
       tamanio = i;
    }

    if (tamanio<2) {
        mNuError++;
        vcMensaje += "-Debe adjuntas el documento de la resolución.\n"
        f_colorea('frmNuevaResolucion', 'DocResolucion', 'red');
    } else {
        f_colorea('frmNuevaResolucion', 'DocResolucion', 'black');
    }
    
    if(mNuError>0)
        alert(vcMensaje);
    
    return mNuError;
}

function f_moveNext() {
    if (f_valida_datos() == 0)
            if (!confirm('¿Está seguro de registrar la resolución?', 'INDECOPI - RESOLUCIONES INDECOPI'))
                return false;
            else 
                return true;
    else {
        //alert('Por favor, verifique y complete los datos marcados en color rojo.');
        return false;
    }
    return true;
}

function f_colorea(mFormulario, mElementId, mTipo) {
    document.getElementById(mFormulario + ':' + mElementId).style.color = mTipo;
}

function fn_validaPreestablecido(){
    console.log(">>fn_validaPreestablecido<<");
    console.log("factu: "+document.getElementById('frmBusqueda:it_cal_mbase_facturacion').value);

    var vMensaje = 'Por favor valide los siguientes datos: \n';
    var nuError = 0;
    var vInstancia = document.getElementById('frmBusqueda:cb_cal_mbase_instancias').value;
    var vRuc = document.getElementById('frmBusqueda:it_cal_mbase_ruc').value;
    var vRsocial = document.getElementById('frmBusqueda:ot_cal_mbase_rsocial').value;
    var vFactu = document.getElementById('frmBusqueda:it_cal_mbase_facturacion').value;
    var vTamempresa = document.getElementById('frmBusqueda:ot_cal_mbase_tamempresa').value;
    var vAfectacion = document.getElementById('frmBusqueda:it_cal_mbase_infracciones').value;
    
    if(vInstancia!= "-1"){
        f_colorea('frmBusqueda','ot_cal_mbase_instancia','Black');
    }else{
        vMensaje+= '-Debe seleccionar Instancia.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_instancia','Red');
    }
    if(vRuc.length > 0){
        f_colorea('frmBusqueda','ot_cal_mbase_ruc','Black');
    }else{
        vMensaje+= '-Debe ingresar RUC del sancionado.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_ruc','Red');
    }
    if(vRsocial.length > 0 && vRsocial!="RUC no encontrado"){
        f_colorea('frmBusqueda','ot_cal_mbase_rsoc','Black');
    }else{
        vMensaje+= '-Debe ingresar Raz\u00F3n social del sancionado.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_rsoc','Red');
    }
    if(vFactu!= 0){
        f_colorea('frmBusqueda','ot_cal_mbase_factu','Black');
    }else{
        vMensaje+= '-Debe ingresar Facturaci\u00F3n anual (S/).\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_factu','Red');
    }
    if(vTamempresa.length > 0){
        f_colorea('frmBusqueda','ot_cal_mbase_aniof','Black');
    }else{
        vMensaje+= '-Debe seleccionar A\u00F1o de facturaci\u00F3n.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_aniof','Red');
    }
    if(vAfectacion!= ""){
        f_colorea('frmBusqueda','ot_cal_mbase_afectacion','Black');
    }else{
        vMensaje+= '-Debe seleccionar Tipo de afectaci\u00F3n.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_afectacion','Red');
    }
    
    if(nuError > 0){
        //alert(vMensaje);
        var iframe = document.createElement("IFRAME");
        iframe.setAttribute("src", 'data:text/plain,');
        document.documentElement.appendChild(iframe);
        window.frames[0].window.alert(vMensaje);
        iframe.parentNode.removeChild(iframe);
        return false;
    }else{
        return true;
    }
}

function fn_validaCcd(){
    console.log(">>fn_validaCcd<<");
    
    var vMensaje = 'Por favor valide los siguientes datos: \n';
    var nuError = 0;
    var vInstancia = document.getElementById('frmBusqueda:cb_cal_mbase_instanciasccd').value;
    var vRuc = document.getElementById('frmBusqueda:it_cal_mbase_rucccd').value;
    var vRsocial = document.getElementById('frmBusqueda:ot_cal_mbase_rsocialccd').value;
    var vFactu = document.getElementById('frmBusqueda:it_cal_mbase_facturacionccd').value;
    var vTamempresa = document.getElementById('frmBusqueda:ot_cal_mbase_tamempresaccd').value;
    var vAfectacion = document.getElementById('frmBusqueda:it_cal_mbase_infraccionesccd').value;
    //var vFd = document.getElementById('frmBusqueda:ot_cal_mbase_factor').value;
    
    if(vInstancia!= "-1"){
        f_colorea('frmBusqueda','ot_cal_mbase_instanciaccd','Black');
    }else{
        vMensaje+= '-Debe seleccionar Instancia.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_instanciaccd','Red');
    }
    if(vRuc.length > 0){
        f_colorea('frmBusqueda','ot_cal_mbase_rucccd','Black');
    }else{
        vMensaje+= '-Debe ingresar RUC del sancionado.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_rucccd','Red');
    }
    if(vRsocial.length > 0 && vRsocial!="RUC no encontrado"){
        f_colorea('frmBusqueda','ot_cal_mbase_rsocccd','Black');
    }else{
        vMensaje+= '-Debe ingresar Raz\u00F3n social del sancionado.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_rsocccd','Red');
    }
    if(vFactu!= 0){
        f_colorea('frmBusqueda','ot_cal_mbase_factuccd','Black');
    }else{
        vMensaje+= '-Debe ingresar Facturaci\u00F3n anual (S/).\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_factuccd','Red');
    }
    if(vTamempresa.length > 0){
        f_colorea('frmBusqueda','ot_cal_mbase_aniofccd','Black');
    }else{
        vMensaje+= '-Debe seleccionar A\u00F1o de facturaci\u00F3n.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_aniofccd','Red');
    }
    if(vAfectacion!= ""){
        f_colorea('frmBusqueda','ot_cal_mbase_afectacionccd','Black');
    }else{
        vMensaje+= '-Debe seleccionar Tipo de afectaci\u00F3n.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_afectacionccd','Red');
    }
    
    if(nuError > 0){
        //alert(vMensaje);
        var iframe = document.createElement("IFRAME");
        iframe.setAttribute("src", 'data:text/plain,');
        document.documentElement.appendChild(iframe);
        window.frames[0].window.alert(vMensaje);
        iframe.parentNode.removeChild(iframe);
        return false;
    }else{
        return true;
    }
}

function fn_validaPi(){
    console.log(">>fn_validaPi<<");
    
    var vMensaje = 'Por favor valide los siguientes datos: \n';
    var nuError = 0;
    var vInstancia = document.getElementById('frmBusqueda:cb_cal_mbase_instanciaspi').value;
    var vRuc = document.getElementById('frmBusqueda:it_cal_mbase_rucpi').value;
    var vRsocial = document.getElementById('frmBusqueda:ot_cal_mbase_rsocialpi').value;
    var vFactu = document.getElementById('frmBusqueda:it_cal_mbase_facturacionpi').value;
    var vTamempresa = document.getElementById('frmBusqueda:ot_cal_mbase_tamempresapi').value;
    var vAfectacion = document.getElementById('frmBusqueda:it_cal_mbase_infraccionespi').value;
    //var vFd = document.getElementById('frmBusqueda:ot_cal_mbase_factor').value;
    
    if(vInstancia!= "-1"){
        f_colorea('frmBusqueda','ot_cal_mbase_instanciapi','Black');
    }else{
        vMensaje+= '-Debe seleccionar Instancia.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_instanciapi','Red');
    }
    if(vRuc.length > 0){
        f_colorea('frmBusqueda','ot_cal_mbase_rucpi','Black');
    }else{
        vMensaje+= '-Debe ingresar RUC del sancionado.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_rucpi','Red');
    }
    if(vRsocial.length > 0 && vRsocial!="RUC no encontrado"){
        f_colorea('frmBusqueda','ot_cal_mbase_rsocpi','Black');
    }else{
        vMensaje+= '-Debe ingresar Raz\u00F3n social del sancionado.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_rsocpi','Red');
    }
    if(vFactu!= 0){
        f_colorea('frmBusqueda','ot_cal_mbase_factupi','Black');
    }else{
        vMensaje+= '-Debe ingresar Facturaci\u00F3n anual (S/).\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_factupi','Red');
    }
    if(vTamempresa.length > 0){
        f_colorea('frmBusqueda','ot_cal_mbase_aniofpi','Black');
    }else{
        vMensaje+= '-Debe seleccionar A\u00F1o de facturaci\u00F3n.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_aniofpi','Red');
    }
    if(vAfectacion!= ""){
        f_colorea('frmBusqueda','ot_cal_mbase_afectacionpi','Black');
    }else{
        vMensaje+= '-Debe seleccionar Tipo de afectaci\u00F3n.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_afectacionpi','Red');
    }
    
    if(nuError > 0){
        //alert(vMensaje);
        var iframe = document.createElement("IFRAME");
        iframe.setAttribute("src", 'data:text/plain,');
        document.documentElement.appendChild(iframe);
        window.frames[0].window.alert(vMensaje);
        iframe.parentNode.removeChild(iframe);
        return false;
    }else{
        return true;
    }
}

function fn_validaFirma(){
    console.log(">>fn_validaFirma<<");
    
    var vMensaje = 'Por favor valide los siguientes datos: \n';
    var nuError = 0;
    var vRuc = document.getElementById('frmBusqueda:it_cal_mbase_rucfirma').value;
    var vRsocial = document.getElementById('frmBusqueda:ot_cal_mbase_rsocialfirma').value;
    var vFactu = document.getElementById('frmBusqueda:it_cal_mbase_facturacionfirma').value;
    var vTamempresa = document.getElementById('frmBusqueda:ot_cal_mbase_tamempresafirma').value;
    var vAfectacion = document.getElementById('frmBusqueda:it_cal_mbase_infraccionesfirma').value;
    var vTipo = document.getElementById('frmBusqueda:cb_cal_mbase_tipfirma').value;
    //var vFd = document.getElementById('frmBusqueda:ot_cal_mbase_factor').value;
    
    if(vTipo!= "-1"){
        f_colorea('frmBusqueda','ot_cal_mbase_tipfirma','Black');
    }else{
        vMensaje+= '-Debe seleccionar Servicio o producto acreditado.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_tipfirma','Red');
    }
    if(vRuc.length > 0){
        f_colorea('frmBusqueda','ot_cal_mbase_rucfirma','Black');
    }else{
        vMensaje+= '-Debe ingresar RUC del sancionado.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_rucfirma','Red');
    }
    if(vRsocial.length > 0 && vRsocial!="RUC no encontrado"){
        f_colorea('frmBusqueda','ot_cal_mbase_rsocfirma','Black');
    }else{
        vMensaje+= '-Debe ingresar Raz\u00F3n social del sancionado.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_rsocfirma','Red');
    }
    if(vFactu!= 0){
        f_colorea('frmBusqueda','ot_cal_mbase_factufirma','Black');
    }else{
        vMensaje+= '-Debe ingresar Facturaci\u00F3n anual (S/).\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_factufirma','Red');
    }
    if(vTamempresa.length > 0){
        f_colorea('frmBusqueda','ot_cal_mbase_anioffirma','Black');
    }else{
        vMensaje+= '-Debe seleccionar A\u00F1o de facturaci\u00F3n.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_anioffirma','Red');
    }
    if(vAfectacion!= ""){
        f_colorea('frmBusqueda','ot_cal_mbase_afectacionfirma','Black');
    }else{
        vMensaje+= '-Debe seleccionar Tipo de afectaci\u00F3n.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_afectacionfirma','Red');
    }
    
    if(nuError > 0){
        //alert(vMensaje);
        var iframe = document.createElement("IFRAME");
        iframe.setAttribute("src", 'data:text/plain,');
        document.documentElement.appendChild(iframe);
        window.frames[0].window.alert(vMensaje);
        iframe.parentNode.removeChild(iframe);
        return false;
    }else{
        return true;
    }
}

function fn_validaAdhoc(){
    console.log(">>fn_validaAdhoc<<");
    console.log("factu: "+document.getElementById('frmBusqueda:it_cal_mbase_facturacionadhoc').value);
    console.log("ruc: "+document.getElementById('frmBusqueda:it_cal_mbase_rucpadhoc').value);
    console.log("rz: "+document.getElementById('frmBusqueda:ot_cal_mbase_rsocialadhoc').value);
    console.log("instancia: "+document.getElementById('frmBusqueda:cb_cal_mbase_instanciasadhoc').value);
    console.log("afect: "+document.getElementById('frmBusqueda:cb_cal_mbase_infraccionesadhoc').value);
    console.log("fb: "+document.getElementById('frmBusqueda:it_cal_mbase_factorbadhoc').value);
    
    var vMensaje = 'Por favor valide los siguientes datos: \n';
    var nuError = 0;
    var vInstancia = document.getElementById('frmBusqueda:cb_cal_mbase_instanciasadhoc').value;
    var vRuc = document.getElementById('frmBusqueda:it_cal_mbase_rucpadhoc').value;
    var vRsocial = document.getElementById('frmBusqueda:ot_cal_mbase_rsocialadhoc').value;
    var vFactu = document.getElementById('frmBusqueda:it_cal_mbase_facturacionadhoc').value;
    var vTamempresa = document.getElementById('frmBusqueda:ot_cal_mbase_tamempresaadhoc').value;
    var vAfectacion = document.getElementById('frmBusqueda:it_cal_mbase_infraccionesadhoc').value;
    var vFb = document.getElementById('frmBusqueda:it_cal_mbase_factorbadhoc').value;
    
    if(vInstancia!= "-1"){
        f_colorea('frmBusqueda','ot_cal_mbase_instanciaadhoc','Black');
    }else{
        vMensaje+= '-Debe seleccionar Instancia.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_instanciaadhoc','Red');
    }
    if(vRuc.length > 0){
        f_colorea('frmBusqueda','ot_cal_mbase_rucadhoc','Black');
    }else{
        vMensaje+= '-Debe ingresar RUC del sancionado.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_rucadhoc','Red');
    }
    if(vRsocial.length > 0 && vRsocial!="RUC no encontrado"){
        f_colorea('frmBusqueda','ot_cal_mbase_rsocadhoc','Black');
    }else{
        vMensaje+= '-Debe ingresar Raz\u00F3n social del sancionado.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_rsocadhoc','Red');
    }
    if(vFactu!= 0){
        f_colorea('frmBusqueda','ot_cal_mbase_factuadhoc','Black');
    }else{
        vMensaje+= '-Debe ingresar Facturaci\u00F3n anual (S/).\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_factuadhoc','Red');
    }
    if(vTamempresa.length > 0){
        f_colorea('frmBusqueda','ot_cal_mbase_aniofadhoc','Black');
    }else{
        vMensaje+= '-Debe seleccionar A\u00F1o de facturaci\u00F3n.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_aniofadhoc','Red');
    }
    if(vAfectacion!= ""){
        f_colorea('frmBusqueda','ot_cal_mbase_afectacionadhoc','Black');
    }else{
        vMensaje+= '-Debe seleccionar Tipo de afectaci\u00F3n.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_afectacionadhoc','Red');
    }
    if(vFb!= 0){
        f_colorea('frmBusqueda','ot_cal_mbase_factorbadhoc','Black');
    }else{
        vMensaje+= '-Debe ingresar Factor "\u00DF".\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_factorbadhoc','Red');
    }
    
    if(nuError > 0){
        //alert(vMensaje);
        var iframe = document.createElement("IFRAME");
        iframe.setAttribute("src", 'data:text/plain,');
        document.documentElement.appendChild(iframe);
        window.frames[0].window.alert(vMensaje);
        iframe.parentNode.removeChild(iframe);
        return false;
    }else{
        return true;
    }
}

function fn_validaVentas(){
    console.log(">>fn_validaVentas<<");
    console.log("factu: "+document.getElementById('frmBusqueda:it_cal_mbase_facturacionadhoc').value);
    console.log("ruc: "+document.getElementById('frmBusqueda:it_cal_mbase_rucpventas').value);
    console.log("rz: "+document.getElementById('frmBusqueda:ot_cal_mbase_rsocialadhoc').value);
    console.log("instancia: "+document.getElementById('frmBusqueda:cb_cal_mbase_instanciaspventas').value);
    console.log("afect: "+document.getElementById('frmBusqueda:cb_cal_mbase_infraccionesadhoc').value);
    console.log("fb: "+document.getElementById('frmBusqueda:it_cal_mbase_factorbadhoc').value);
    
    var vMensaje = 'Por favor valide los siguientes datos: \n';
    var nuError = 0;
    var vInstancia = document.getElementById('frmBusqueda:cb_cal_mbase_instanciaspventas').value;
    var vRuc = document.getElementById('frmBusqueda:it_cal_mbase_rucpventas').value;
    var vRsocial = document.getElementById('frmBusqueda:ot_cal_mbase_rsocialpventas').value;
    var vFactu = document.getElementById('frmBusqueda:it_cal_mbase_facturacionpventas').value;
    var vFactuProd = document.getElementById('frmBusqueda:it_cal_mbase_facturacionprodpventas').value;
    //var vTamempresa = document.getElementById('frmBusqueda:ot_cal_mbase_tamempresaadhoc').value;
    var vAnio = document.getElementById('frmBusqueda:cb_cal_mbase_anioUitpventas').value;
    var vAfectacion = document.getElementById('frmBusqueda:it_cal_mbase_infraccionespventas').value;
    var vFa = document.getElementById('frmBusqueda:it_cal_mbase_factorapventas').value;
    
    if(vInstancia!= "-1"){
        f_colorea('frmBusqueda','ot_cal_mbase_instanciapventas','Black');
    }else{
        vMensaje+= '-Debe seleccionar Instancia.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_instanciapventas','Red');
    }
    if(vAnio!= "-1"){
        f_colorea('frmBusqueda','ot_cal_mbase_aniofpventas','Black');
    }else{
        vMensaje+= '-Debe seleccionar A\u00F1o de facturaci\u00F3n.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_aniofpventas','Red');
    }
    if(vRuc.length > 0){
        f_colorea('frmBusqueda','ot_cal_mbase_rucpventas','Black');
    }else{
        vMensaje+= '-Debe ingresar RUC del sancionado.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_rucpventas','Red');
    }
    if(vRsocial.length > 0 && vRsocial!="RUC no encontrado"){
        f_colorea('frmBusqueda','ot_cal_mbase_rsocpventas','Black');
    }else{
        vMensaje+= '-Debe ingresar Raz\u00F3n social del sancionado.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_rsocpventas','Red');
    }
    if(vFactu!= 0){
        f_colorea('frmBusqueda','ot_cal_mbase_factupventas','Black');
    }else{
        vMensaje+= '-Debe ingresar Facturaci\u00F3n anual (S/).\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_factupventas','Red');
    }
    if(vFactuProd!= 0){
        f_colorea('frmBusqueda','ot_cal_mbase_factuprodpventas','Black');
    }else{
        vMensaje+= '-Debe ingresar Ventas del producto o servicio durante el periodo de infracci\u00F3n (V).\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_factuprodpventas','Red');
    }
    if(vAfectacion!= ""){
        f_colorea('frmBusqueda','ot_cal_mbase_afectacionpventas','Black');
    }else{
        vMensaje+= '-Debe seleccionar Caracter\u00EDsticas del nivel de disuasi\u00F3n.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_afectacionpventas','Red');
    }
    if(vFa!= 0){
        f_colorea('frmBusqueda','lblot_cal_mbase_factorapventas','Black');
    }else{
        vMensaje+= '-Debe ingresar Factor "a".\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','lblot_cal_mbase_factorapventas','Red');
    }
    
    if(nuError > 0){
        //alert(vMensaje);
        var iframe = document.createElement("IFRAME");
        iframe.setAttribute("src", 'data:text/plain,');
        document.documentElement.appendChild(iframe);
        window.frames[0].window.alert(vMensaje);
        iframe.parentNode.removeChild(iframe);
        return false;
    }else{
        return true;
    }
}

function fn_validaBarreras(){
    console.log(">>fn_validaBarreras<<");
    var vMensaje = 'Por favor valide los siguientes datos: \n';
    var nuError = 0;
    var vInfractor = document.getElementById('frmBusqueda:cb_cal_mbase_tipbarreras').value;
    var vRuc = document.getElementById('frmBusqueda:it_cal_mbase_rucbar').value;
    var vRsocial = document.getElementById('frmBusqueda:ot_cal_mbase_rsocialbar').value;
    var vFactu = document.getElementById('frmBusqueda:it_cal_mbase_facturacionbarreras').value;
    //var vTamempresa = document.getElementById('frmBusqueda:ot_cal_mbase_tamempresaadhoc').value;
    var vAnio = document.getElementById('frmBusqueda:cb_cal_mbase_anioUitbarreras').value;
    var vAgente = document.getElementById('frmBusqueda:cb_cal_mbase_alcbarreras').value;
    var vPoblacion = document.getElementById('frmBusqueda:cb_cal_mbase_fpobbarreras').value;
    var vAfectacion = document.getElementById('frmBusqueda:it_cal_mbase_infraccionesbarreras').value;
    
    var vProbabilidad = document.getElementById('frmBusqueda:ot_cal_mbase_probresubarreras').value;
    
    if(vInfractor!= "-1"){
        f_colorea('frmBusqueda','ot_cal_mbase_tipbarreras','Black');
    }else{
        vMensaje+= '-Debe seleccionar Tipo infractor.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_tipbarreras','Red');
    }
    if(vAnio!= "-1"){
        f_colorea('frmBusqueda','ot_cal_mbase_aniofbarreras','Black');
    }else{
        vMensaje+= '-Debe seleccionar A\u00F1o de facturaci\u00F3n.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_aniofbarreras','Red');
    }
    if(vPoblacion!= "-1"){
        f_colorea('frmBusqueda','ot_cal_mbase_fpobbarreras','Black');
    }else{
        vMensaje+= '-Debe seleccionar Factor poblaci\u00F3n.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_fpobbarreras','Red');
    }
    if(vAgente!= "-1"){
        f_colorea('frmBusqueda','ot_cal_mbase_alcbarreras','Black');
    }else{
        vMensaje+= '-Debe seleccionar Tipo de agente afectado.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_alcbarreras','Red');
    }
    if(vRuc.length > 0){
        f_colorea('frmBusqueda','ot_cal_mbase_rucbar','Black');
    }else{
        vMensaje+= '-Debe ingresar RUC del sancionado.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_rucbar','Red');
    }
    if(vRsocial.length > 0 && vRsocial!="RUC no encontrado"){
        f_colorea('frmBusqueda','ot_cal_mbase_rsocbar','Black');
    }else{
        vMensaje+= '-Debe ingresar Raz\u00F3n social del sancionado.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_rsocbar','Red');
    }
    if(vFactu!= 0){
        f_colorea('frmBusqueda','ot_cal_mbase_factubarreras','Black');
    }else{
        vMensaje+= '-Debe ingresar Facturaci\u00F3n anual (S/).\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_factubarreras','Red');
    }
    if(vAfectacion!= ""){
        f_colorea('frmBusqueda','ot_cal_mbase_afectacionbarreras','Black');
    }else{
        vMensaje+= '-Debe seleccionar Tipo de afectaci\u00F3n.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_afectacionbarreras','Red');
    }
    if(vProbabilidad!= 0){
        f_colorea('frmBusqueda','ot_cal_mbase_probbarreras','Black');
    }else{
        vMensaje+= '-Debe seleccionar Nivel de probabilidad de detecci\u00F3n.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_probbarreras','Red');
    }
    
    if(nuError > 0){
        //alert(vMensaje);
        var iframe = document.createElement("IFRAME");
        iframe.setAttribute("src", 'data:text/plain,');
        document.documentElement.appendChild(iframe);
        window.frames[0].window.alert(vMensaje);
        iframe.parentNode.removeChild(iframe);
        return false;
    }else{
        return true;
    }
}

function fn_validaLibro(){
    console.log(">>fn_validaLibro<<");
    var vMensaje = 'Por favor valide los siguientes datos: \n';
    var nuError = 0;
    //var vInstancia = document.getElementById('frmBusqueda:cb_cal_mbase_instancias').value;
    var vRuc = document.getElementById('frmBusqueda:it_cal_mbase_ruclib').value;
    var vRsocial = document.getElementById('frmBusqueda:ot_cal_mbase_rsociallib').value;
    var vFactu = document.getElementById('frmBusqueda:it_cal_mbase_facturacionlib').value;
    var vTamempresa = document.getElementById('frmBusqueda:ot_cal_mbase_tamempresalib').value;
    var vAfectacion = document.getElementById('frmBusqueda:it_cal_mbase_infraccioneslib').value;
    //var vFd = document.getElementById('frmBusqueda:ot_cal_mbase_factor').value;
    
    
    if(vRuc.length > 0){
        f_colorea('frmBusqueda','ot_cal_mbase_ruclib','Black');
    }else{
        vMensaje+= '-Debe ingresar el RUC del sancionado.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_ruclib','Red');
    }
    if(vRsocial.length > 0 && vRsocial!="RUC no encontrado"){
        f_colorea('frmBusqueda','ot_cal_mbase_rsoclib','Black');
    }else{
        vMensaje+= '-Debe ingresar Raz\u00F3n social del sancionado.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_rsoclib','Red');
    }
    if(vFactu!= 0){
        f_colorea('frmBusqueda','ot_cal_mbase_factulib','Black');
    }else{
        vMensaje+= '-Debe ingresar Facturaci\u00F3n anual (S/).\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_factulib','Red');
    }
    if(vTamempresa.length > 0){
        f_colorea('frmBusqueda','ot_cal_mbase_anioflib','Black');
    }else{
        vMensaje+= '-Debe seleccionar el A\u00F1o de facturaci\u00F3n.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_anioflib','Red');
    }
    if(vAfectacion!= ""){
        f_colorea('frmBusqueda','ot_cal_mbase_afectacionlib','Black');
    }else{
        vMensaje+= '-Debe seleccionar el Tipo de afectaci\u00F3n.\n'; 
        nuError += 1;
        f_colorea('frmBusqueda','ot_cal_mbase_afectacionlib','Red');
    }
    
    if(nuError > 0){
        //alert(vMensaje);
        var iframe = document.createElement("IFRAME");
        iframe.setAttribute("src", 'data:text/plain,');
        document.documentElement.appendChild(iframe);
        window.frames[0].window.alert(vMensaje);
        iframe.parentNode.removeChild(iframe);
        return false;
    }else{
        return true;
    }
}

function fn_validaExpediente(){
    var vMensaje = 'Por favor valide los siguientes datos: \n';
    var nuError = 0;
    var vNroExp = document.getElementById('formExpediente:txtNroExp').value;
    var vAnioExp = document.getElementById('formExpediente:txtAnioExp').value;
    //var vSiglaExp = document.getElementById('formExpediente:cboSiglaExpedientecomboboxField').value;
    
    //Validar Número de expediente
    if(vNroExp.length > 0){
     f_colorea('formExpediente','NroExpediente','Black');
    }else{
     vMensaje+= '-Debe ingresar el número de expediente.\n'; 
     nuError += 1;
     f_colorea('formExpediente','NroExpediente','Red');
    }
    //Validar Año de expediente 
    if(vAnioExp.length > 0){
     f_colorea('formExpediente','NroExpediente','Black');
    }else{
     vMensaje+= '-Debe ingresar el año del expediente.\n'; 
     nuError += 1;
     f_colorea('formExpediente','NroExpediente','Red');
    }
    //Validar Sigla de expediente
    /*if(vSiglaExp.length > 0){
     f_colorea('formExpediente','NroExpediente','Black');
    }else{
     vMensaje+= '-Debe ingresar la sigla del expediente.\n'; 
     nuError += 1;
     f_colorea('formExpediente','NroExpediente','Red');
    }*/
    if(nuError > 0){
        alert(vMensaje);
        return false;
    }else{
        return true;
    }
}

function Valida_Dato(e, opc) {
                //Segun la opcion se habilita o no el espacio
                tecla = (document.all) ? e.keyCode : e.which;
                if (tecla == 8 || tecla == 0)
                    return true;//Tecla de retroceso (para poder borrar) 
                switch (opc) {
                    case 1:
                    //Para numeros enteros en general. Solicitudes, cedulas,nit, numeros de identificacion, etc
                        patron = /[1234567890-]/;//solo numeros y lineas
                        break;

                    case 2:
                    //Para texto largo general
                        if (tecla==225 || tecla==233 || tecla==237 || tecla==243 || tecla==250 || 
                            tecla==193 || tecla==201 || tecla==205 || tecla==211 || tecla==218) return true;
                        if (tecla == 32)
                            return true;//Tecla de espacio
                        patron = /[a-zA-ZÑñ1234567890,.()-ÁÉÍÓÚáéíóú]/;//caracteres y numeros
                        patron = /\w/;//caracteres y numeros
                        break;

                    case 3:
                    //Para numeros telefonicos 
                        if (tecla == 32)
                            return true;//Tecla de espacio
                    //    patron = /[EXText1234567890.()-]/;
                          patron = /[EXTextAaNnOo1234567890.()-]/;
                        break;

                    case 4:
                    //para campos que necesitan valores numericos de precios 
                        patron = /[1234567890.,]/;//solo numeros puntos y comas
                        break;

                    case 6:
                    //Para validar campos de nombres y apellidos
                        if (tecla==225 || tecla==233 || tecla==237 || tecla==243 || tecla==250 || 
                            tecla==193 || tecla==201 || tecla==205 || tecla==211 || tecla==218) return true;
                        if (tecla == 32)
                            return true;//Tecla de espacio
                        patron = /[a-zA-ZÑñ]/;
                        break;

                    case 7:
                    //Para validar email
                        patron = /[a-zA-ZÑñ1234567890@.-_]/;
                        break;

                    case 8:
                    //Para números enteros positivos DNI
                        patron = /[1234567890]/;//solo numeros enteros positivos
                        break;

                    case 9:
                    //Direcciones 
                        if (tecla==225 || tecla==233 || tecla==237 || tecla==243 || tecla==250 || 
                            tecla==193 || tecla==201 || tecla==205 || tecla==211 || tecla==218) return true;
                        if (tecla == 32)
                            return true;//Tecla de espacio
                        patron = /[a-zA-ZÑñ1234567890.(),-]/;
                        break;

                    case 10:
                    //Caracteres y números
                        if (tecla == 32)
                            return true;
                        patron = /[a-zA-ZÑñ1234567890]/;
                        break;
                    case 11:
                    //Caracteres
                        patron = /[abcdefghijklmnñopqrstuvxyzwAZBCDEFGHIJKLMNOPQRSTUVWXYÑñÁÉÍÓÚáéíóú]/;//caracteres
                        break;                            
                    case 12:
                    //para campos que necesitan valores numericos de precios 
                        patron = /[1234567890.]/;//solo numeros puntos y comas
                        break;
                        
                    default: 
                        return true;    
                }
                te = String.fromCharCode(tecla);
                return patron.test(te);
            }
            
function seleccionarTodoDoc(source) {
    checkboxes = document.getElementsByTagName('input');
    for (i = 0;i < checkboxes.length;i++)
    {
        if (checkboxes[i].type == "checkbox" && checkboxes[i].id != "frmConversion:tbDocProcesados:cbxSelTodosDoc")
        {
            checkboxes[i].checked = source.checked;
        }
    }
}


function isPresDayEnabled(day) {  
    
    var curDt = new Date();

    
    var prvDt = new Date();

    prvDt.setDate(prvDt.getDate() - 8); // cambio temporal

    
    var slcDt = new Date(day.date);
     
        return (slcDt.getTime() < curDt.getTime());

}

function getPresDisabledStyle(day) {
    if (!isPresDayEnabled(day)){
        return 'rich-calendar-boundary-dates disabledDay';
    }

}

function check(e) {
    tecla = (document.all) ? e.keyCode : e.which;

    //Tecla de retroceso para borrar, siempre la permite
    if (tecla == 8) {
        return true;
    }

    // Patron de entrada, en este caso solo acepta numeros y letras
    patron = /[A-Za-z0-9]/;
    tecla_final = String.fromCharCode(tecla);
    return patron.test(tecla_final);
}

function checkFactorAValido(field1, field2, field3,organo) {
    console.log("organo: "+document.getElementById(organo).value);
    console.log("nuUmbral: "+document.getElementById(field1).value);
    console.log("nuFactorA: "+document.getElementById(field2).value);
    mNuError = 0;
    var vcMensaje;
    var nuUmbral = document.getElementById(field1).value;
    var nuFactorA = document.getElementById(field2).value;

    if(Math.floor(nuFactorA*100) > Math.floor(nuUmbral*100) ){
        mNuError++;
        document.getElementById(field3).style.color = 'red';
        vcMensaje = 'Por favor valide los siguientes datos: \n-El factor ingresado debe ser menor a: '+nuUmbral+ '.\n';
        document.getElementById('frmBusqueda:bt_cal_mbase_calcularMbasepventas').disabled = "true";

    }else{
        document.getElementById(field3).style.color = 'black';
        document.getElementById('frmBusqueda:bt_cal_mbase_calcularMbasepventas').removeAttribute("disabled");
    }

    if(mNuError>0)
        alert(vcMensaje);

    return mNuError;
}

function checkRuc(field1) {
    console.log("ruc: "+document.getElementById(field1).value);
    var vMensaje;

    if(document.getElementById(field1).value.length==0 ){
        //document.getElementById('frmBusqueda:ot_cal_mbase_rucbar').style.color = 'red';
        vMensaje = 'Por favor ingrese RUC del sancionado.\n';
        //document.getElementById('frmBusqueda:bt_cal_mbase_calcularMbasepventas').disabled = "true";
        //alert(vMensaje);
        var iframe = document.createElement("IFRAME");
        iframe.setAttribute("src", 'data:text/plain,');
        document.documentElement.appendChild(iframe);
        window.frames[0].window.alert(vMensaje);
        iframe.parentNode.removeChild(iframe);
    }else{
        //document.getElementById('frmBusqueda:ot_cal_mbase_rucbar').style.color = 'black';
        //document.getElementById('frmBusqueda:bt_cal_mbase_calcularMbasepventas').removeAttribute("disabled");
    }
        

}

function checkNumeros(e){
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 8) {
        return true;
    }
    /* if (tecla == 190) {
        return true;
    }
    if (tecla == 13) {
        return true;
    } */
    
    patron=/^[0-9]+(.[0-9]+)?$/
    tecla_final = String.fromCharCode(tecla);
    return patron.test(tecla_final);
}

function checkNumerosDecimal(e){
    tecla = (document.all) ? e.keyCode : e.which;
    console.log("tecla: "+tecla);
    if (tecla == 8) {
        return true;
    }
    /* if (tecla == 190) {
        return true;
    }
    if (tecla == 13) {
        return true;
    } */
    
    patron=/^(\d+(\.\d{0,2})?|\.?\d{1,2})$/
    tecla_final = String.fromCharCode(tecla);
    console.log("tecla_final: "+tecla_final);
    return patron.test(tecla_final);
}

function ifEnterClick(event, targetElement, valid, targetElement2) {
    event = event || window.event;
    if (event.keyCode == 13) {
        // normalize event target, so it looks the same for all browsers
        if (!event.target) {
            event.target = event.srcElement;
        }

        // don't do anything if the element handles the enter key on its own
        if (event.target.nodeName == 'A') {
            return;
        }
        if (event.target.nodeName == 'INPUT') {
            if (event.target.type == 'button' || event.target.type == 'submit') {
                if (strEndsWith(event.target.id, 'focusKeeper')) {
                    // inside some Richfaces component such as rich:listShuttle
                } else {
                    return;
                }
            }
        }
        //if (event.target.nodeName =='TEXTAREA') {
        //    return;
        //}

        // swallow event
        if (event.preventDefault) {
            // Firefox
            event.stopPropagation();
            event.preventDefault();
        } else {
            // IE
            event.cancelBubble = true;
            event.returnValue = false;
        }
        if(document.getElementById(valid).value!=-1){
            document.getElementById(targetElement).click();
            document.getElementById(targetElement2).click();
        }else{
            //alert("Debe seleccionar Año de facturación ó remuneración.");
            var iframe = document.createElement("IFRAME");
            iframe.setAttribute("src", 'data:text/plain,');
            document.documentElement.appendChild(iframe);
            window.frames[0].window.alert("Debe seleccionar A\u00F1o de facturaci\u00F3n \u00F3 A\u00F1o de remuneraci\u00F3n.");
            iframe.parentNode.removeChild(iframe);
        }
        
    }
}