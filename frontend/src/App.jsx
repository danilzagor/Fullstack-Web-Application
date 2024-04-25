import './App.css';
import Home from './Home';
import ListOfEmployees from './ListOfEmployees';
import NavBar from './NavBar';
import {BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import SpecificEmployee from './SpecificEmployee';
import NewEmployee from './NewEmployee';
import Login from './Login';
import PrivateRouter from './PrivateRouter';

function App() {
  return (
    <div className="App">
      <Router>
        <NavBar></NavBar>
        <Routes>
        <Route path='/' element={<Home></Home>}></Route>
        <Route path='/list' element={<ListOfEmployees></ListOfEmployees>}></Route>
        <Route path='/list/:id' element={<SpecificEmployee></SpecificEmployee>}></Route>
        <Route path='/newEmployee' element={<NewEmployee></NewEmployee>}></Route>
        <Route path="*" element={<PrivateRouter/>} />
        </Routes>
        
      </Router>
      
    </div>
  );
}

export default App;
