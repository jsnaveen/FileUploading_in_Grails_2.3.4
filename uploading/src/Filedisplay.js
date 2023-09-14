import React, { useState } from 'react';
import './FileDisplay.css';

function FileDisplay() {
  const [fileId, setFileId] = useState('');
  const [fileData, setFileData] = useState(null);

  const handleGetFile = () => {
    if (!fileId) return;

    fetch(`http://localhost:8080/FileUploadAPIAssessment/getFile/${fileId}`)
      .then((response) => response.blob())
      .then((blobData) => {
        setFileData(blobData);
      })
      .catch((error) => {
        console.error('Error getting file:', error);
      });
  };

  return (
    <div className="file-display-container">
      <div className="input-container">
        <input
          type="text"
          placeholder="Enter File ID"
          value={fileId}
          onChange={(e) => setFileId(e.target.value)}
        />
        <button onClick={handleGetFile} className="get-file-button">
          Show File
        </button>
      </div>
      {fileData && (
        <div className="file-preview">
          {fileData.type.includes('image') ? (
            <img
              src={URL.createObjectURL(fileData)}
              alt="Retrieved File"
              style={{ maxWidth: '100%', maxHeight: '400px' }}
            />
          ) : (
            <a
              href={URL.createObjectURL(fileData)}
              target="_blank"
              rel="noopener noreferrer"
            >
              View File
            </a>
          )}
        </div>
      )}
    </div>
  );
}

export default FileDisplay;
