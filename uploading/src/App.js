import React from "react";
import "./App.css";
import FileDisplay from "./Filedisplay";
import FileUpload from "./Fileupload";
import StoredDataList from './StoredDataList';

function App() {
  const appStyles = { 
    fontFamily: "Arial, sans-serif",
    backgroundColor: "#f5f5f5",
    padding: "20px",
    textAlign: "top center",
  };

  return (
    <div className="App" style={appStyles}>
      <h1>File Uploading and Display</h1>
      <FileUpload />
      <FileDisplay />
      <StoredDataList/>
    </div>
  );
}

export default App;
