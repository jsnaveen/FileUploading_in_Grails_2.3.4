package fileuploadapiassessment

class UploadedFile {
    
    byte[] fileContent           // Store the binary content of the uploaded file
    String originalFilename      // Store the original filename of the uploaded file
    String contentType           // Store the content type of the uploaded file

    static constraints = { 
        fileContent maxSize: 5 * 1024 * 1024 // 5 MB in bytes
    }
}


POST - http://localhost:8080/FileUploadAPIAssessment/upload
GET  - http://localhost:8080/FileUploadAPIAssessment/getFile/4