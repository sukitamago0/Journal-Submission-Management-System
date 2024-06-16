document.getElementById('uploadForm').addEventListener('submit', function(event) {
    event.preventDefault(); // 阻止表单默认提交

    var fileInput = document.getElementById('fileInput');
    var file = fileInput.files[0]; // 获取文件对象

    if (file) {
        var formData = new FormData();
        formData.append('file', file);

        fetch('/upload', {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                console.log('File uploaded successfully:', data);
            })
            .catch(error => {
                console.error('Error uploading file:', error);
            });
    } else {
        console.log('No file selected');
    }
});
