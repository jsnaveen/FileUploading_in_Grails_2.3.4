package fileuploadapiassessment

import grails.transaction.Transactional
import org.springframework.http.HttpStatus

class FileUploadController {

    @Transactional
    def upload() {
        
        def uploadedFile = request.getFile('file')  // Retrieve the uploaded file from the request

        if (!uploadedFile) {
           
            render(status: HttpStatus.BAD_REQUEST.value(), text: "No file uploaded") // If no file is uploaded, return a bad request response
            return
        }

        // Validate file type
        def allowedExtensions = ['jpg', 'jpeg', 'png', 'pdf', 'docx']
        def fileExtension = uploadedFile.originalFilename.split('\\.').last().toLowerCase()

        if (!allowedExtensions.contains(fileExtension)) {
           
            render(status: HttpStatus.BAD_REQUEST.value(), text: "Unsupported file type") // If the file type is not allowed, return a bad request response
            return
        }

        // Validate file size
        if (uploadedFile.size > 5 * 1024 * 1024) {
           
            render(status: HttpStatus.BAD_REQUEST.value(), text: "File size exceeds 5 MB") // If the file size is too large, return a bad request response
            return
        }

        // Store the file in the database
        def newUploadedFile = new UploadedFile(
            fileContent: uploadedFile.bytes,
            originalFilename: uploadedFile.originalFilename,
            contentType: uploadedFile.contentType
        )
        newUploadedFile.save()
        // Return a success response
        render(status: HttpStatus.OK.value(), text: "File uploaded successfully")
    }

    def getFile(Long fileId) {
        
        def uploadedFile = UploadedFile.get(fileId) // Retrieve the uploaded file from the database by fileId
        if (!uploadedFile) {
           
            render(status: HttpStatus.NOT_FOUND.value(), text: "File not found")    // If the file is not found, return a not found response
            return
        }

        // Set the response headers for content type and disposition
        response.setContentType(uploadedFile.contentType)
        response.setHeader("Content-Disposition", "inline; filename=${uploadedFile.originalFilename}")

        // Write the file content to the response output stream
        response.outputStream << uploadedFile.fileContent
        response.outputStream.flush()
        response.outputStream.close()
    }
}
