import axios from "axios"

const LAMETRO_API_BASE_URL = "http://localhost:8080/";
const LAMETRO_KAFKA_BASE_URL = "http://localhost:8081/";

class MetroService{
    getVehicles(){
        return axios.get(LAMETRO_API_BASE_URL + "vehicles");
    }

    getHistory(){
        return axios.get(LAMETRO_API_BASE_URL + "history");
    }

    getEvents(){
        return axios.get(LAMETRO_KAFKA_BASE_URL + "events");
    }
}

export default new MetroService();
