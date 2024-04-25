import { useEffect, useState } from "react";

const useFetch = (url) => {
    const [data, setData] = useState(null);
    const [isPending, setisPending] = useState(true);
    const [error, setError] = useState(null);
    useEffect(() => {
        const abortController = new AbortController();
        fetch(url, { signal: abortController.signal })
            .then(res => {
                if (!res.ok) {
                    throw Error('could not fetch the data');
                }
                return res.json();
            }).then((data) => {
                setData(data);
                setisPending(false);
                setError(null);
            }).catch((error) => {
                if (error.name !== 'AbortError') {
                    setisPending(false);
                    setError(error);
                }
            })
            return ()=>abortController.abort();
    },[url]);
    return {
        data,isPending,error
    };
}

export default useFetch;