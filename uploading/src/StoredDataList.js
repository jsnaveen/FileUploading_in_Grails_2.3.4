import React, { useEffect, useState } from "react";
import "./StoredDataList.css"; // Import your CSS file

function StoredDataList() {
  const [storedData, setStoredData] = useState([]);
  const [isTableView, setIsTableView] = useState(true); // Start in table view

  useEffect(() => {
    fetch("http://localhost:8080/FileUploadAPIAssessment/api/storedFiles")
      .then((response) => response.json())
      .then((data) => {
        setStoredData(data);
      })
      .catch((error) => {
        console.error("Error fetching stored data:", error);
      });
  }, []);

  const toggleView = () => {
    setIsTableView(!isTableView);
  };

  return (
    <div className="stored-data-list">
      <h2>Stored Files List</h2>
      <button onClick={toggleView} className="toggle-button">
        {isTableView ? "Hide" : "Show Stored Data"}
      </button>
      {isTableView && (
        <table className="data-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Original Filename</th>
              <th>Content Type</th>
            </tr>
          </thead>
          <tbody>
            {storedData.map((item) => (
              <tr key={item.id}>
                <td>{item.id}</td>
                <td>{item.originalFilename}</td>
                <td>{item.contentType}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default StoredDataList;
