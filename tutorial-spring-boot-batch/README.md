# **Spring boot batch 사용**

## batch 사용법

## 설정사항
### DB
1. datasource 3개 연결
2. 스크립트 초기화(batch meta data, test schema)

#### mysql 사용
#### 로컬과 원격 저장소
#### 로컬은 데이터 저장 스키마, batch meta data 저장 스키마
<table>
  <tr>
    <th>데이터베이스</th>
    <th colspan="2">로컬</th>
    <th>원격</th>
  </tr>
  <tr>
    <td>구분</td>
    <td>로컬 저장소</td>
    <td>batch meta data(로컬에 저장)</td>
    <td>aws</td>
  </tr>
  <tr>
    <td>호스트</td>
    <th colspan="2">localhost</th>
    <th>aws(localhost)</th>
  </tr>
  <tr>
    <td>스키마</td>
    <td>test</td>
    <td>batch</td>
    <td>data</td>
  </tr>
</table>

