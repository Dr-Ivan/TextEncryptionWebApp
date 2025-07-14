async function encrypt() {
    const inp1 = document.getElementById("inp1");
    const key = document.getElementById("inp2");
    const res = document.getElementById("res");

    inputText = inp1.value;
    inputKey = key.value;
    
    try {
        const response = await fetch('https://derevyanko.braverto.com/api/processVigenerCipherEncryptionRequest', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ 
                text: inputText,
                key: inputKey
                })
        });

        if (!response.ok) throw new Error('Ошибка сервера');
        
        const result = await response.text();
        //alert(result);
        res.value = JSON.parse(result).text;

    } catch (error) {
        console.log(`Ошибка: ${error.message}`);
    };
};

async function decrypt() {
    const inp1 = document.getElementById("inp1");
    const key = document.getElementById("inp2");
    const res = document.getElementById("res");

    inputText = inp1.value;
    inputKey = key.value;
    
    try {
        const response = await fetch('https://derevyanko.braverto.com/api/processVigenerCipherDecryptionRequest', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ 
                text: inputText,
                key: inputKey
                })
        });

        if (!response.ok) throw new Error('Ошибка сервера');
        
        const result = await response.text();
        //alert(result);
        res.value = JSON.parse(result).text;

    } catch (error) {
        console.log(`Ошибка: ${error.message}`);
    };
};

function clearResult() {
    const resField = document.getElementById("res");
    resField.value = "";
}
