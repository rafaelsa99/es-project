import axios from "axios"
//const LAMETRO_API_BASE_URL = "http://localhost:52042/";
const LAMETRO_API_BASE_URL = "http://192.168.160.87:52042/";

class MetroService{
    getParks(){
        return axios.get(LAMETRO_API_BASE_URL + "parkinglots");
    }

    getAll(){
        return axios.get(LAMETRO_API_BASE_URL + "agencies");
    }

    getBus(){
        return axios.get(LAMETRO_API_BASE_URL + "agencies/lametro");
    }

    getMetro(){
        return axios.get(LAMETRO_API_BASE_URL + "agencies/lametro-rail");
    }

    getHistory(){
        return axios.get(LAMETRO_API_BASE_URL + "history");
    }

    getEvents(){
        return axios.get(LAMETRO_API_BASE_URL + "events");
    }

    getLastEvent(){
        return axios.get(LAMETRO_API_BASE_URL + "events/last");
    }
}

export default new MetroService();
