package pe.com.tech.portal.empleado.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.tech.portal.empleado.bean.PayLoadBean;
import pe.com.tech.portal.empleado.enumerado.EnumWeb;
import pe.com.tech.portal.empleado.security.auth.JwtAuthenticationToken;
import pe.com.tech.portal.empleado.security.model.UserContext;

public class Util {  

    public static boolean esNuloVacio(String param) {
        if (param == null) return true;
        if (param.trim().length() == 0) return true;
        return false;
    }      
    // Util - WEB
    public static boolean isAjax(HttpServletRequest request) {
        return EnumWeb.XML_HTTP_REQUEST.getValue().equals(request.getHeader(EnumWeb.X_REQUESTED_WITH.getValue()));
    }

    public static boolean isAjax(SavedRequest request) {
        return request.getHeaderValues(EnumWeb.X_REQUESTED_WITH.getValue()).contains(EnumWeb.XML_HTTP_REQUEST.getValue());
    }

    public static boolean isContentTypeJson(SavedRequest request) {
        return request.getHeaderValues(EnumWeb.CONTENT_TYPE.getValue()).contains(EnumWeb.CONTENT_TYPE_JSON.getValue());
    }
  
    public static String obtenerUsuarioSession(JwtAuthenticationToken request) {
        // TODO Auto-generated method stub        
        UserContext usuarioBean;
        try {
            usuarioBean = (UserContext) request.getPrincipal();
            return usuarioBean.getUsername();
        } catch (Exception e) {
            // TODO: handle exception
            return "";
        }

    }
  
    public static String formatAsJson(Object object) throws Exception {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(object);
    }
    
    public static BigDecimal parseDoubleToBigDecimal(Double porcentajeParticipacion) {
        // TODO Auto-generated method stub
        BigDecimal response = new BigDecimal(porcentajeParticipacion * 100).setScale(15, BigDecimal.ROUND_HALF_UP);
        return response;
    }
    
    public static String parseBigDecimalToString(BigDecimal ingresoPromedio, String numericMask, String prefijoSoles) {
        // TODO Auto-generated method stub
        double temporal;
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator(',');
        DecimalFormat formatDouble = new DecimalFormat(numericMask, otherSymbols);

        try {
            temporal = ingresoPromedio.doubleValue();
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            return "";
        }
        return prefijoSoles.concat(formatDouble.format(temporal).trim());

    }

    public static String formatoMonedaMascara(String monto, String numericMask, String prefijoSoles) {
        // TODO Auto-generated method stub
        double temporal;
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator(',');

        DecimalFormat formatDouble = new DecimalFormat(numericMask, otherSymbols);

        try {
            temporal = Double.parseDouble(monto);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            return "";
        }
        return prefijoSoles.concat(formatDouble.format(temporal).trim());
    }

    public static String formatFechaMascara(String fecha, String formatoFecha) {
        // TODO Auto-generated method stub
        SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
        String response = "";
        // format BD
        SimpleDateFormat sdfBD = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaParse = null;
        try {

            fechaParse = sdfBD.parse(fecha.substring(0, 10));

        } catch (java.text.ParseException e) {
            try {
                fechaParse = sdf.parse(fecha);
            } catch (java.text.ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        if (fechaParse == null) return "";

        response = sdf.format(fechaParse);
        return response;
    }   

    public static double roundPorcentaje(double param) {

        if (param == 0) return param;
        BigDecimal bd = new BigDecimal(Double.toString(param));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    public static boolean verifyPasswordBcrypt(String clave, String claveDB) {
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	return passwordEncoder.matches(clave, claveDB);    	
    }
    
    public static String decodePasswordBcrypt(String clave) {
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(clave);
		return hashedPassword ;
    }
    
    public static String obtenerTokenAcceso(HttpServletRequest request) {
        // TODO Auto-generated method stub
        String tokenId = "";
        try {
            String authorization = request.getHeader("Authorization");
            if (authorization != null && authorization.contains("Bearer")) {
                tokenId = authorization.replace("Bearer", "");
            }
            String[] contenidoToken = tokenId.split("\\.");
            return contenidoToken[1];
        } catch (Exception e) {
            // TODO: handle exception
            return "";
        }
    }
    
    private static String descifrarBase64(String arg) {
        // TODO Auto-generated method stub
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByteArray = decoder.decode(arg);

        return new String(decodedByteArray);
    }
    
    public static String getUserNameCurrent(HttpServletRequest request) {
        // TODO Auto-generated method stub
    	ObjectMapper mapper = new ObjectMapper();                
        PayLoadBean bean = new PayLoadBean();        
        try {            
            bean = mapper.readValue(descifrarBase64(obtenerTokenAcceso(request)), PayLoadBean.class);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
        return bean.getSub().toLowerCase().trim();        
    }
    
    public static String traceException(Throwable e) {
        StringWriter sw = new StringWriter();
        if (e.getCause() != null)
            e.getCause().printStackTrace(new PrintWriter(sw));
        else
            e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

}
