import axios from "axios"
const LAMETRO_API_BASE_URL = "http://localhost:8087/";

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
