 expect default = ImageToBase64(files) {
                // let files = document.getElementById('upImageFile').files[0];
                var reader = new FileReader()
                reader.readAsDataURL(files)
                reader.onload = () => {
                    console.log('file 转 base64结果：' + reader.result)
                    return  reader.result
                }
                reader.onerror = function (error) {
                    console.log('Error: ', error)
                }
            }

