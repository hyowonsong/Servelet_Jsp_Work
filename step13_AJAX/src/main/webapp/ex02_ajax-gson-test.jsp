<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Insert title here</title>
  </head>
  <body>
    <h3>Ajax Gson Test</h3>
    <input type="button" value="text결과" id="ajaxBtn" />
    <input type="button" value="json결과" id="jsonBtn" />

    <input type="button" value="DTO결과" id="dtoBtn" />
    <input type="button" value="List결과" id="listBtn" />
    <input type="button" value="Map결과" id="mapBtn" />

    <input type="button" value="textJson결과" id="textJsonBtn" />

    <hr />
    <div id="display"></div>

    <script>
      const baseURL = `${pageContext.request.contextPath}`; // 서버에서 EL로 처리가 되어 문자열로 온다.
      console.log(baseURL);

      // text 결과
      document.getElementById("ajaxBtn").addEventListener("click", async () => {
        try {
          // 1. jsp에서는 백틱을 해석하기 전에 EL로 보기 때문에 이렇게는 안된다.
          //const response = await fetch(`${baseURL}/ajax`, {
          // 2. 해결된 버전 - 백틱을 해석
          const response = await fetch(`${"${baseURL}"}/ajax`, {
            method: "POST",
            body: new URLSearchParams({
              key: "text",
            }),
          });

          if (!response.ok)
            throw new Error("text 요청 실패 " + response.status);

          const result = await response.text();
          document.getElementById("display").innerHTML = result;
        } catch (err) {
          document.getElementById("display").innerHTML = err;
        }
      });
      ///////////////////////////////////////////////////////////////////////////////

      // json 결과
      document.getElementById("jsonBtn").addEventListener("click", async () => {
        try {
          // 1. jsp에서는 백틱을 해석하기 전에 EL로 보기 때문에 이렇게는 안된다.
          //const response = await fetch(`${baseURL}/ajax`, {
          // 2. 해결된 버전 - 백틱을 해석
          const response = await fetch(`${"${baseURL}"}/ajax`, {
            method: "POST",
            body: new URLSearchParams({
              // 바꾸면 여기를 바꾸면 된다.
              key: "json",
            }),
          });

          if (!response.ok)
            throw new Error("text 요청 실패 " + response.status);

          // 여기 바꾸면 된다.
          const result = await response.json();
          console.log(result);

          let str = "";
          result.forEach((item) => {
            str +=
              "<input type='checkbox' name='menu' value='" + item + "'>" + item;
          });

          document.getElementById("display").innerHTML = str;
        } catch (err) {
          document.getElementById("display").innerHTML = err;
        }
      });

      // dto 결과
      document.getElementById("dtoBtn").addEventListener("click", async () => {
        try {
          // 1. jsp에서는 백틱을 해석하기 전에 EL로 보기 때문에 이렇게는 안된다.
          //const response = await fetch(`${baseURL}/ajax`, {
          // 2. 해결된 버전 - 백틱을 해석
          const response = await fetch(`${"${baseURL}"}/ajax`, {
            method: "POST",
            body: new URLSearchParams({
              // 바꾸면 여기를 바꾸면 된다.
              key: "dto",
            }),
          });

          if (!response.ok)
            throw new Error("text 요청 실패 " + response.status);

          // 여기 바꾸면 된다.
          const result = await response.json();
          console.log(result);

          // dto는 Member 쪽 꺼내오면 된다.
          document.getElementById("display").innerHTML =
            result.id + "|" + result.name + "|" + result.age;
        } catch (err) {
          document.getElementById("display").innerHTML = err;
        }
      });

      // list 결과
      document.getElementById("listBtn").addEventListener("click", async () => {
        try {
          // 1. jsp에서는 백틱을 해석하기 전에 EL로 보기 때문에 이렇게는 안된다.
          //const response = await fetch(`${baseURL}/ajax`, {
          // 2. 해결된 버전 - 백틱을 해석
          const response = await fetch(`${"${baseURL}"}/ajax`, {
            method: "POST",
            body: new URLSearchParams({
              // 바꾸면 여기를 바꾸면 된다.
              key: "list",
            }),
          });

          if (!response.ok)
            throw new Error("text 요청 실패 " + response.status);

          // 여기 바꾸면 된다.
          const result = await response.json();
          console.log(result);

          let str = "";
          result.forEach((member) => {
            str += member.id + "|" + member.name + "|" + member.age;
          });

          // dto는 Member 쪽 꺼내오면 된다.
          document.getElementById("display").innerHTML = str;
        } catch (err) {
          document.getElementById("display").innerHTML = err;
        }
      });

      // map 결과
      document.getElementById("mapBtn").addEventListener("click", async () => {
        try {
          // 1. jsp에서는 백틱을 해석하기 전에 EL로 보기 때문에 이렇게는 안된다.
          //const response = await fetch(`${baseURL}/ajax`, {
          // 2. 해결된 버전 - 백틱을 해석
          const response = await fetch(`${"${baseURL}"}/ajax`, {
            method: "POST",
            body: new URLSearchParams({
              // 바꾸면 여기를 바꾸면 된다.
              key: "map",
            }),
          });

          if (!response.ok)
            throw new Error("text 요청 실패 " + response.status);

          // 여기 바꾸면 된다.
          const result = await response.json();
          console.log(result);

          let str = result.pageNo + "<br>";
          str += result.message + "<br>";
          str += result.dto.id + " | " + result.dto.name + "<br>";
          str += "<hr>";
          result.memberList.forEach((member) => {
            str += member.name + "<br>";
          });

          // dto는 Member 쪽 꺼내오면 된다.
          document.getElementById("display").innerHTML = str;
        } catch (err) {
          document.getElementById("display").innerHTML = err;
        }
      });
    </script>
  </body>
</html>
