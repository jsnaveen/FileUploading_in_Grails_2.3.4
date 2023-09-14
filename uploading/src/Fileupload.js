import React, { useState } from "react";
import "./FileUpload.css"; // Import your CSS file

function Fileupload() {
  const [selectedFile, setSelectedFile] = useState(null);

  const handleFileChange = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  const handleFileUpload = () => {
    if (!selectedFile) {
      window.alert("Please select a file before uploading.");
      return;
    }

    const formData = new FormData();
    formData.append("file", selectedFile);

    fetch("http://localhost:8080/FileUploadAPIAssessment/upload", {
      method: "POST",
      body: formData,
    })
      .then((response) => {
        if (response.status === 200) {
          return response.json();
        } else {
          throw new Error(`File upload failed with status: ${response.status}`);
        }
      })
      .then((data) => {
        console.log("File uploaded:", data);
        // Alert for successful upload
        window.alert("File uploaded successfully!");
      })
      .catch((error) => {
        console.error("Error uploading file:", error.message); // Log the specific error message
        // Alert for failed upload with error message
        window.alert("Error uploading file.");
      });
  };

  return (
    <div className="file-upload-container">
      <label htmlFor="fileInput" className="custom-file-input">
        Choose File
      </label>
      <input
        type="file"
        id="fileInput"
        onChange={handleFileChange}
        className="file-input"
      />
      {selectedFile && <div className="file-name">{selectedFile.name}</div>}
      <button onClick={handleFileUpload} className="choose-file-button">
        Upload
      </button>
    </div>
  );
}

export default Fileupload;
