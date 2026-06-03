import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [activePattern, setActivePattern] = useState(null);
  const [singletonData, setSingletonData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [patterns, setPatterns] = useState({});

  const API_BASE_URL = 'http://localhost:8080/api/singleton';

  // Fetch all patterns info on mount
  useEffect(() => {
    fetchAllPatterns();
  }, []);

  const fetchAllPatterns = async () => {
    try {
      const response = await fetch(`${API_BASE_URL}/all`);
      const data = await response.json();
      setPatterns(data.patterns);
    } catch (error) {
      console.error('Error fetching patterns:', error);
    }
  };

  const fetchSingletonData = async (endpoint) => {
    setLoading(true);
    try {
      const response = await fetch(`${API_BASE_URL}/${endpoint}`);
      const data = await response.json();
      setSingletonData(data);
      setActivePattern(endpoint);
    } catch (error) {
      console.error('Error fetching singleton data:', error);
      setSingletonData({ error: 'Failed to fetch data. Make sure backend is running.' });
    } finally {
      setLoading(false);
    }
  };

  const PatternButton = ({ label, endpoint }) => (
    <button
      className={`pattern-btn ${activePattern === endpoint ? 'active' : ''}`}
      onClick={() => fetchSingletonData(endpoint)}
    >
      {label}
    </button>
  );

  const PatternDetails = ({ label, details }) => (
    <div className="pattern-details-item">
      <h4>{label}</h4>
      <p><strong>Description:</strong> {details.description}</p>
      <p><strong>Advantage:</strong> {details.advantage}</p>
      <p><strong>Disadvantage:</strong> {details.disadvantage}</p>
    </div>
  );

  return (
    <div className="App">
      <header className="app-header">
        <h1>Singleton Design Pattern Demo</h1>
        <p>Explore different implementations of the Singleton pattern in Java</p>
      </header>

      <main className="app-main">
        <section className="patterns-section">
          <h2>Choose a Singleton Pattern</h2>
          <div className="pattern-buttons">
            <PatternButton label="Eager Singleton" endpoint="eager" />
            <PatternButton label="Lazy Singleton" endpoint="lazy" />
            <PatternButton label="Double-Checked Locking" endpoint="double-checked" />
            <PatternButton label="Bill Pugh" endpoint="bill-pugh" />
            <PatternButton label="Enum Singleton" endpoint="enum" />
            <PatternButton label="Database Connection" endpoint="database" />
          </div>
        </section>

        {loading && (
          <div className="loading">
            <p>Loading...</p>
          </div>
        )}

        {singletonData && !loading && (
          <section className="result-section">
            <h2>Pattern Details</h2>
            <div className="result-card">
              {singletonData.error ? (
                <p className="error">{singletonData.error}</p>
              ) : (
                <>
                  <div className="result-header">
                    <h3>{singletonData.type}</h3>
                  </div>
                  
                  <div className="result-body">
                    {singletonData.message && (
                      <p><strong>Pattern:</strong> {singletonData.message}</p>
                    )}
                    
                    {singletonData.description && (
                      <p><strong>Description:</strong> {singletonData.description}</p>
                    )}
                    
                    {singletonData.instance && (
                      <p><strong>Instance Hash:</strong> <code>{singletonData.instance}</code></p>
                    )}
                    
                    {singletonData.instance1 && singletonData.instance2 && (
                      <>
                        <p><strong>First Call Hash:</strong> <code>{singletonData.instance1}</code></p>
                        <p><strong>Second Call Hash:</strong> <code>{singletonData.instance2}</code></p>
                        <p className={singletonData.isSameInstance ? 'success' : 'error'}>
                          <strong>Same Instance:</strong> {singletonData.isSameInstance ? '✓ Yes' : '✗ No'}
                        </p>
                      </>
                    )}
                    
                    {singletonData.isConnected !== undefined && (
                      <>
                        <p><strong>Connected:</strong> {singletonData.isConnected ? '✓ Yes' : '✗ No'}</p>
                        {singletonData.connectionString && (
                          <p><strong>Connection String:</strong> <code>{singletonData.connectionString}</code></p>
                        )}
                      </>
                    )}
                  </div>
                </>
              )}
            </div>
          </section>
        )}

        <section className="patterns-overview">
          <h2>All Singleton Patterns Overview</h2>
          <div className="patterns-grid">
            {Object.entries(patterns).map(([key, details]) => (
              <PatternDetails 
                key={key} 
                label={
                  key === 'eager' ? 'Eager Initialization' :
                  key === 'lazy' ? 'Lazy Initialization' :
                  key === 'doubleChecked' ? 'Double-Checked Locking' :
                  key === 'billPugh' ? 'Bill Pugh (Static Inner Class)' :
                  key === 'enum' ? 'Enum Singleton' :
                  'Database Connection'
                }
                details={details}
              />
            ))}
          </div>
        </section>
      </main>

      <footer className="app-footer">
        <p>Singleton Pattern Demo | Spring Boot & React</p>
      </footer>
    </div>
  );
}

export default App;
