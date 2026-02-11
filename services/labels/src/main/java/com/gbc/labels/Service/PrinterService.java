package com.gbc.labels.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbc.labels.dto.CustomMultipartFile;
import com.gbc.labels.dto.DocumentRequest;
import com.gbc.labels.dto.EpaLabelRequest;
import com.gbc.labels.dto.Valores;
import org.springframework.stereotype.Service;



import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;





@Service
public class PrinterService {



    public void imprimir(EpaLabelRequest epaLabelRequest) throws IOException {
        //Socket clientSocket=new Socket("192.168.1.243",6101);
        System.out.println("imprimiendo");
        //DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream() );
        String tranid = epaLabelRequest.getTranid();
        String upc = epaLabelRequest.getUpc();
        String direntrega = epaLabelRequest.getDirentrega();
        String description = epaLabelRequest.getDescription();
        String json = epaLabelRequest.getCustcol_pslad_inventory_details_json();
        System.out.println(json);
        ObjectMapper objectMapper = new ObjectMapper();
        Valores valores = objectMapper.readValue(json, Valores.class);
        String caducidad = valores.getValues().get(0).getPslad_lot_expiration_date();
        String lote = valores.getValues().get(0).getPslad_lot_expiration_date();

        String zplCommand =String.format("^XA\n" +
                "~TA000\n" +
                "~JSN\n" +
                "^LT0\n" +
                "^MNW\n" +
                "^MTT\n" +
                "^PON\n" +
                "^PMN\n" +
                "^LH0,0\n" +
                "^JMA\n" +
                "^PR4,4\n" +
                "~SD15\n" +
                "^JUS\n" +
                "^LRN\n" +
                "^CI27\n" +
                "^PA0,1,1,0\n" +
                "^XZ\n" +
                "^XA\n" +
                "^MMT\n" +
                "^PW799\n" +
                "^LL1598\n" +
                "^LS0\n" +
                "^FO23,1133^GFA,2697,9720,24,:Z64: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:CE5D\n" +
                "^FT99,589^A0B,76,76^FH\\^CI28^FDOrden de Salida^FS^CI27\n" +
                "^FT179,533^A0B,55,56^FH\\^CI28^FDEPA_25_9067^FS^CI27\n" +
                "^FT238,533^A0B,36,35^FH\\^CI28^FDFecha:^FS^CI27\n" +
                "^FT236,372^A0B,34,33^FH\\^CI28^FD20/02/2025^FS^CI27\n" +
                "^FT278,1559^A0B,37,38^FH\\^CI28^FDCliente:^FS^CI27\n" +
                "^FT349,1563^A0B,37,38^FH\\^CI28^FDDirección Entrega:^FS^CI27\n" +
                "^FT427,1438^A0B,35,35^FH\\^CI28^FDProducto^FS^CI27\n" +
                "^FT431,505^A0B,35,35^FH\\^CI28^FDDescripción^FS^CI27\n" +
                "^FT427,1114^A0B,35,35^FH\\^CI28^FDUPC^FS^CI27\n" +
                "^FT427,956^A0B,35,35^FH\\^CI28^FDCantidad^FS^CI27\n" +
                "^FT427,732^A0B,35,35^FH\\^CI28^FDLotes^FS^CI27\n" +
                "^FT432,230^A0B,31,30^FH\\^CI28^FDFecha Caducidad^FS^CI27\n" +
                "^BY2,3,126^FT765,938^BCB,,Y,N\n" +
                "^FH\\^FD>;123456789012^FS\n" +
                "^FT279,1399^A0B,32,33^FH\\^CI20^FD%s^FS^CI27\n" +
                "^FT350,1245^A0B,32,33^FH\\^CI20^FD%s^FS^CI27\n" +
                "^FT471,1559^A0B,30,30^FH\\^CI28^FD%s^FS^CI27\n" +
                "^FT466,1132^A0B,30,30^FH\\^CI28^FD%s^FS^CI27\n" +
                "^FT466,930^A0B,30,30^FH\\^CI28^FD%s^FS^CI27\n" +
                "^FT471,579^A0B,30,30^FH\\^CI28^FD%s^FS^CI27\n" +
                "^FT468,162^A0B,28,28^FH\\^CI28^FD%s^FS^CI27\n" +
                "^FT471,757^A0B,30,3m0^FH\\^CI28^FD%s^FS^CI27\n" +
                "^PQ1,,,Y\n" +
                "^XZ", direntrega, direntrega, description.substring(0,30), upc, lote,description,caducidad, tranid);
        System.out.println(zplCommand);
        //outToServer.writeBytes(zplCommand);




       // clientSocket.close();
    }

    public void pruebaZpl(String zpl, String nombre) {

        String url = "http://api.labelary.com/v1/printers/8dpmm/labels/4x8/0/";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))

                .POST(HttpRequest.BodyPublishers.ofString(zpl))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofByteArray())
                .thenApply(HttpResponse::body)
                .thenAccept(body -> {

                    try {

                        MultipartFile multipartFile = new CustomMultipartFile("file", nombre + ".png", "image/png", body);

                        DocumentRequest documentRequest = DocumentRequest.builder().file(multipartFile).build();
                       // oneDriveService.cargarArchivo(documentRequest);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .join();
    }
}
